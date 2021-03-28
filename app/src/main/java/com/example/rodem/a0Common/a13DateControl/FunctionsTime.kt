package vlm.naimanmaster.a1Functions.a13DateControl


fun stringToTime(timeString : String) : Long{ // 1초를 1000ms 로 바꿔 줘야한다

    val minInt = timeString.split(":")[0].toInt()
    val secInt = timeString.split(":")[1].toInt()
    return     (minInt*6000 + secInt*1000).toLong()

}