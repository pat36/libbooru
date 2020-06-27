package ovh.fandemonium.libbooru

import ovh.fandemonium.libbooru.{Danbooru, Gelbooru, Safebooru}

class Parser {
    def getFiles(booru: String, tags: Array[String], limit: Int): Any = {
        booru match {
            case "danbooru" => Danbooru.getFiles(tags, limit)
            case "gelbooru" => Gelbooru.getFiles(tags, limit)
            case "safebooru" => Safebooru.getFiles(tags, limit)
        }
    }
}