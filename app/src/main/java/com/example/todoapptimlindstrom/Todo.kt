package XXX

data class ToDo(
        val id: Int,
        var title: String,
        var content: String,
  //      val ischecked: Boolean = false
) {

    override fun toString() = title

}