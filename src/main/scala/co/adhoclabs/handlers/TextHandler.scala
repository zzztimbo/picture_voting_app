package co.adhoclabs.handlers

import co.adhoclabs.stateful.PictureVoteCounter

class TextHandler(val pictureVote: String) extends PictureSetHandler {

  def isValidPicture(picture: String) = getPicturesSet().contains(picture)

  def vote(): Unit = {
    if (isValidPicture(pictureVote)) {
      PictureVoteCounter.vote(pictureVote)
    }
  }
}

object TextHandler {
  def apply(pictureVote: String) = new TextHandler(pictureVote).vote()
}
