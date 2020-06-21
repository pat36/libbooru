package ovh.fandemonium.libbooru

import java.net.URL
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.{Paths, Files}
import scala.xml.XML
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import sys.process._
import scala.language.postfixOps

object Gelbooru {
    def getFiles(tags: Array[String], limit: Int): Any = {
        val files = Gelbooru.getFilesLinks(tags, limit)

        for(file <- files) {
            println(Gelbooru.downloadFile(file("url"), file("name")))
            Thread.sleep(1000)
        }
    }

    def getFilesLinks(tags: Array[String], limit: Int): Array[Map[String, String]] = {
        val tags_str = tags.mkString(" ")
        val api_str = s"https://gelbooru.com/index.php?page=dapi&s=post&q=index&limit=$limit&tags=$tags_str&json=0"
        val xml = XML.load(api_str)
        val posts_arr = ArrayBuffer[Map[String, String]]();
        val posts = xml\"post"

        for(post <- posts) {
            val url = post\@"file_url"
            val tags = post\@"tags"
            val md5 = post\@"md5"
            val url_segments = new URL(url).getFile.split("/")
            val name = url_segments(url_segments.length - 1)
            val post_map = Map("url" -> url, "tags" -> tags, "md5" -> md5, "name" -> name)

            posts_arr += post_map
        }

        posts_arr.toArray
    }

    def downloadFile(url: String, name: String): String = {
        val directory = Paths.get(".").toAbsolutePath + "/img/"
        if(!new File(directory).exists) {
            new File(directory).mkdir
        }
        val filename = directory  + name
        new URL(url) #> new File(filename) !!;
        filename
    }
}
