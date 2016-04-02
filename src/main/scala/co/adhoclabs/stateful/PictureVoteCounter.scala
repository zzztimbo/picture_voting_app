package co.adhoclabs.stateful

import co.adhoclabs.handlers.PictureSetHandler

object PictureVoteCounter extends PictureSetHandler {
  private val pictureVoteCounts = collection.mutable.Map[String, Int]().withDefaultValue(0)

  def vote(pictureName: String) = {
    pictureVoteCounts(pictureName) += 1
  }

  def getVotes = {
    val curPicturesSet = getPicturesSet()
    val unvotedPictures = curPicturesSet -- pictureVoteCounts.keys
    unvotedPictures.foreach(pic => pictureVoteCounts(pic) = 0)
    collection.immutable.Map(pictureVoteCounts.toSeq: _*)
  }
}
