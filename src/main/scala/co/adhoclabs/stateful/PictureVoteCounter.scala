package co.adhoclabs.stateful

import co.adhoclabs.handlers.PictureSetHandler

object PictureVoteCounter extends PictureSetHandler {
  private val pictureVoteCounts = collection.mutable.Map[String, Int]().withDefaultValue(0)

  def vote(pictureName: String) = {
    pictureVoteCounts(pictureName) += 1
    println(s">>>> $pictureVoteCounts")
  }

  def getVotes = {
    val curPicturesSet = getPicturesSet()
    println(s">>>> $curPicturesSet")
    val unvotedPictures = curPicturesSet -- pictureVoteCounts.keys
    println(s">>>>>> $unvotedPictures")
    unvotedPictures.foreach(pic => pictureVoteCounts(pic) = 0)
    println(s">>>>>>>> $pictureVoteCounts")
    println(s">>>>>>>> ${collection.immutable.Map(pictureVoteCounts.toSeq: _*)}")
    collection.immutable.Map(pictureVoteCounts.toSeq: _*)
  }
}
