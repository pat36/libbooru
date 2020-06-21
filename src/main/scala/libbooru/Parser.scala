package ovh.fandemonium.libbooru

import ovh.fandemonium.libbooru.{Danbooru, Gelbooru}

class Parser {
    def getFiles(booru: String, tags: Array[String], limit: Int): Any = {
        booru match {
            case "danbooru" => Danbooru.getFiles(tags, limit)
            case "gelbooru" => Gelbooru.getFiles(tags, limit)
        }
    }
}