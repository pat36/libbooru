package ovh.fandemonium.libbooru

import java.net.URL
import java.io.File
import java.nio.file.{Paths, Files}
import sys.process._
import scala.language.postfixOps

trait Booru {
    def getFiles(tags: Array[String], limit: Int): Any

    def getFilesLinks(tags: Array[String], limit: Int): Array[Map[String, String]]

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