package ovh.fandemonium.libbooru

import ovh.fandemonium.libbooru.Parser


object Main {
    def main(args: Array[String]): Unit = {
        if(args.length > 2) {
            val parser = new Parser
            val limit: Int = args(args.length - 1).toInt
            val tags: Array[String] = args.slice(1, args.length - 1)
            parser.getFiles(args(0), tags, limit)
        } else {
            println("args:")
            println("<booru name> <tag1> [<tag2> <tag3> ... <tagn>] <limit>")
            println("available boorus:")
            println("* gelbooru")
            println("* danbooru")
            println("but you __really__ should use it as a library and not as a program")
        }
    }
}