package ovh.fandemonium.libbooru

import java.net.{MalformedURLException, URL}
import java.io.FileWriter
import java.io.IOException
import scala.xml.XML
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Safebooru extends Booru {
    def getFiles(tags: Array[String], limit: Int): Any = {
        val files = Safebooru.getFilesLinks(tags, limit)

        for(file <- files) {
            println(Safebooru.downloadFile(file("url"), file("name")))
            Thread.sleep(1000)
        }
    }

    def getFilesLinks(tags: Array[String], limit: Int): Array[Map[String, String]] = {
        val tags_str = tags.mkString(" ")
        val api_str = s"https://safebooru.org/index.php?page=dapi&s=post&q=index&limit=$limit&tags=$tags_str&json=0"
        val xml = XML.load(api_str)
        val posts_arr = ArrayBuffer[Map[String, String]]();
        val posts = xml\"post"

        for(post <- posts) {
            try {
                val url = post\@"file_url"
                val tags = post\@"tags"
                val md5 = post\@"md5"
                val url_segments = new URL(url).getFile.split("/")
                val name = url_segments(url_segments.length - 1)
                val post_map = Map("url" -> url, "tags" -> tags, "md5" -> md5, "name" -> name)

                posts_arr += post_map
            } catch {
                case e: MalformedURLException => println("couldn't download file")
            }
        }

        posts_arr.toArray
    }
}
