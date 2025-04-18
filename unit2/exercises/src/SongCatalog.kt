class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {
    //val isPopular = playCount>=1000

    val isPopular: Boolean
        get() = playCount >= 1000

    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}

fun main() {
    val song = Song("titel", "artist", 2010, 9)
    song.printSongDescription()
    println(song.isPopular)
}