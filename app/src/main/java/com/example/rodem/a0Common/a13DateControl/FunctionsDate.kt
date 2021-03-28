package vlm.naimanmaster.a1Functions.a13DateControl

import android.annotation.SuppressLint
import android.os.Build
import com.example.rodem.a0Common.a0Object.GlobalFirebaseObject.fireStore
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.floor
import kotlin.math.max

/**
 * -----최종 버전-----------------------------------------------------------------
 * [timeLapseComplete] :  * /**inputTime을 넣으면 1일 이상 지날시, 며칠전으로만 표시, 1일 이전 경과시, 몇시간전으로 표시, 1시간 이전 경과시 몇분전으로 표시해줌**/
 * ----------------------------------------------------------------------------------
 * [timestamptoDate] : 파이어베이스 timestamp를 date로 바꿔준다
 * [firebaseTimeConverter] 파이어베이스 Timestamp값이 인풋 (String리턴) : postingTime(Timestamp, JavaStyle)을 String으로 변환시켜줌.
 * [firebaseTimeConverterKorea] firebaseTimeConverter의 아웃풋을 한국기준의 언어로 나타낸다
 * [ampmTimekorea] 한국기준으로 오전 오후 시간으로 나타내는 함수
 * [yearMonthCompare] : 월만 나타내는 함수
 * [yearMonthDayCompare] 한국시간 기준으로 연도 월 일만 나타내는 함수
 * [nowDateStringFirebase] (파이어베이스) (Date리턴): 파이어베이스 컬랙션 "timechecker"에 현재 시간을 쓰고, 이를 다시 반환해서 파이어베이스 시간을 기입함(읽기 1회, 쓰기1회 소모)
 * [dataGapBetweenTwoDate] : 두 시각 차이 비교(String, String)
 * [nowDateString]: (로컬)현재 시간을 String포맷으로 반환 (timeZone : String = "Asia/Seoul",  DateFormat : String = "YYYY-MM-dd HH:mm:ss")
 * [generateRandAdder] (filename : String) : 파일이름에 랜덤숫자를 붙여서 리턴해줌
 * [timeHMSLapsefromNow] : input Date를 넣으면 timeContainer가 아웃풋으로 나옴
 * [nowDate] : 현재 시간을 아웃풋  date 로 내보냄

**/


data class TimeContainer(
    val day    : String = "0",
    val hour   : String = "0",
    val minute : String = "0",
    val second : String = "0"
)


//firebase timestamp를 date포맷으로 바꾸어준다.
@SuppressLint("SimpleDateFormat")
fun timestamptoDate(date : Any?) : Date
{
    var dateTrans = Date()
    try{
        dateTrans = (date as Timestamp).toDate()
    }catch (e : Exception)
    {
    }
    return dateTrans
}

//타임스탬프 시간을 String포맷으로 바꾸어줌 : 사용 예제 firebaseTimeConverter(Timestamp, 지역설정, 표시스타일)
@SuppressLint("SimpleDateFormat")
fun firebaseTimeConverter(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "yyyy-MM-dd HH:mm:ss") : String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        when(date){
            is Date->{
                val postingTimeLocalDateTime = LocalDateTime.ofInstant((date).toInstant(), ZoneId.of(TimeZone))
                DateTimeFormatter.ofPattern(DateFormat).format(postingTimeLocalDateTime)
            }else ->{
            val postingTimeLocalDateTime = LocalDateTime.ofInstant((date as Timestamp).toDate().toInstant(), ZoneId.of(TimeZone))
            DateTimeFormatter.ofPattern(DateFormat).format(postingTimeLocalDateTime)
        }
        }
    } else {
        when(date){
            is Date->{
                val formatter = SimpleDateFormat(DateFormat)
                formatter.format(date)
            }else ->{
            val formatter = SimpleDateFormat(DateFormat)
            formatter.format((date as Timestamp).toDate())
        }
        }
        val formatter = SimpleDateFormat(DateFormat)
        formatter.format((date as Timestamp).toDate())
    }

@SuppressLint("SimpleDateFormat")
fun generateTodayDate(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "yyyy-MM-dd") : String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        when(date){
            is Date->{
                val postingTimeLocalDateTime = LocalDateTime.ofInstant((date).toInstant(), ZoneId.of(TimeZone))
                DateTimeFormatter.ofPattern(DateFormat).format(postingTimeLocalDateTime)
            }else ->{
            val postingTimeLocalDateTime = LocalDateTime.ofInstant((date as Timestamp).toDate().toInstant(), ZoneId.of(TimeZone))
            DateTimeFormatter.ofPattern(DateFormat).format(postingTimeLocalDateTime)
        }
        }
    } else {
        when(date){
            is Date->{
                val formatter = SimpleDateFormat(DateFormat)
                formatter.format(date)
            }else ->{
            val formatter = SimpleDateFormat(DateFormat)
            formatter.format((date as Timestamp).toDate())
        }
        }
        val formatter = SimpleDateFormat(DateFormat)
        formatter.format((date as Timestamp).toDate())
    }

@SuppressLint("SimpleDateFormat")
fun firebaseTimeConverterKorea(date : Any?, TimeZone : String = "Asia/Seoul", DateFormat : String = "yyyy-MM-dd HH:mm:ss") : String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // date의 타입이 timestamp 일때 오류 날거임
        when(date){
            is Date->{
                val postingTimeLocalDateTime = LocalDateTime.ofInstant((date as Date).toInstant(), ZoneId.of(TimeZone))
                DateTimeFormatter.ofPattern(DateFormat).withLocale(Locale.KOREA).format(postingTimeLocalDateTime)
            }else->{
            val postingTimeLocalDateTime = LocalDateTime.ofInstant((date as Timestamp).toDate().toInstant(), ZoneId.of(TimeZone))
            DateTimeFormatter.ofPattern(DateFormat).withLocale(Locale.KOREA).format(postingTimeLocalDateTime)
        }
        }

    } else {
        when(date){
            is Date->{
                val formatter = SimpleDateFormat(DateFormat)
                formatter.format(date)
            }else->{
            val formatter = SimpleDateFormat(DateFormat)
            formatter.format((date as Timestamp).toDate())  // 여기에 왜 .toDate()가 안들어 가지지..
        }
        }

    }


fun ampmTimekorea(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "a hh:mm ") : String{

    return firebaseTimeConverterKorea(date,TimeZone,DateFormat)
}

fun yearMonthCompare(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "YYYY년 MM월") : String{

    return firebaseTimeConverterKorea(date,TimeZone,DateFormat)
}

//사용예시
//m22_tv_withme_date.text = "개설일 : ${yearMonthDayCompare(withmecontent["postingTime"], DateFormat = "YY.MM.dd")}"
fun yearMonthDayCompare(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "YYYY년 MM월 dd일") : String{
    return firebaseTimeConverterKorea(date,TimeZone,DateFormat)
}
fun yearMonthDayTimeCompare(date : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "YYYY년 MM월 dd일 a HH시 mm분") : String{
    return firebaseTimeConverterKorea(date,TimeZone,DateFormat)
}




//@SuppressLint("SimpleDateFormat")
//fun firebaseTimestamp(postingTime : Any?, TimeZone : String = "Asia/Seoul",  DateFormat : String = "YYYY-MM-dd HH:mm:ss") : String =
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val postingTimeLocalDateTime = LocalDateTime.ofInstant(postingTime.toDate().toInstant(), ZoneId.of(TimeZone))
//        DateTimeFormatter.ofPattern(DateFormat).format(postingTimeLocalDateTime)
//    } else {
//        val formatter = SimpleDateFormat(DateFormat)
//        formatter.format(postingTime.toDate())
//    }


//파이어베이스 컬랙션 "timechecker"에 현재 시간을 쓰고, 이를 다시 반환해서 파이어베이스 시간을 기입함
//사용 예제 : nowDateStringFirebase{ }
fun nowDateStringFirebase(observer:(Date) -> Unit){
    val messages = hashMapOf<String, Any?>()
    messages["currentTime"] = FieldValue.serverTimestamp()
    val storeRef = fireStore.collection("timechecker").document("time")
    storeRef.set(messages, SetOptions.merge()).addOnSuccessListener {
        storeRef.get().addOnSuccessListener {
            observer((it["currentTime"] as Timestamp).toDate())
        }
            .addOnFailureListener {
                // println(it)
                //   Log.e("임시에러체크2 : ", it.toString())
            }
    }
        .addOnFailureListener {
            //  println(it)
            // Log.e("임시에러체크 : ", it.toString())
        }
}

/**inputTime을 넣으면 1일 이상 지날시, 며칠전으로만 표시, 1일 이전 경과시,
 *  몇시간전으로 표시, 1시간 이전 경과시 몇분전으로 표시해줌**/

fun timeLapseComplete(lapseDate : Date) : String
    = try {timeLapseComplete(timeHMSLapsefromNow(lapseDate))} catch (e:Exception){"unknown"}

fun timeLapseComplete(lapseTimeContainer : TimeContainer) : String
{
    val remWeek = lapseTimeContainer.day.toInt().div(7)
    return when{
        lapseTimeContainer.day.toInt() == -1
        -> {
            "방금전(1분 미만)"
        }
        lapseTimeContainer.day.toInt() > 14
        -> {
            "${remWeek}주 전"
        }
        lapseTimeContainer.day.toInt() in 2..13
        -> {
            "${lapseTimeContainer.day}일 전"
        }
        lapseTimeContainer.day.toInt() == 1 //&&  lapseTimeContainer.hour.toInt() > 1
        -> {
            "${24 + lapseTimeContainer.hour.toInt()}시간 전"  //시간단위로 표시
        }
        else //day가 0일때이다
        -> {
            if(lapseTimeContainer.hour.toInt() > 1)
                "${lapseTimeContainer.hour.toInt()}시간 전"
            else
                "${lapseTimeContainer.minute}분 전"
        }
    }
}


///*타임스탬프로 시간을 입력하면 일/시/분/초로 나누어 출력해준다.*/
fun timeHMSLapsefromNow(receivedTime : Any) : TimeContainer = when(receivedTime) {
    is Date -> {
        timeParser((nowDate().time - (receivedTime).time) / 1000)
    }
    is Timestamp -> {
        timeParser((nowDate().time - (receivedTime as Date).time) / 1000)
    }
    else -> {
        try {
            // Any가 그대로 들어왔을때는 최후의 시도로 timeStamp라고 가정하고 date형식으로 바꿔본다
            timeParser((nowDate().time - (receivedTime as Date).time) / 1000)
        } catch (e: java.lang.Exception) {
            timeParser()
        }
    }
}


///*타임스탬프로 시간을 입력하면 초로 나누어 출력해준다.*/
fun timeSLapsefromNow(receivedTime : Any?) : Long =
    when(receivedTime)
    {
        is Date -> {
            ((nowDate().time - (receivedTime).time) / 1000)
        }
        is Timestamp -> {
            try{ //Kotlin Timestamp의 경우 아래가 실행된다.
            ((nowDate().time - (receivedTime as Date).time) / 1000)
            }catch (e: Exception)
            {
                try{ //Firebase Timestamp인 경우 이쪽으로 들어온다.
                    ((nowDate().time - receivedTime.toDate().time) / 1000)
                }
                catch (e: Exception)
                {
                    (-1).toLong()
                }
            }
        }
        else -> {
            try {
                // Any가 그대로 들어왔을때는 최후의 시도로 timeStamp라고 가정하고 date형식으로 바꿔본다
                ((nowDate().time - (receivedTime as Date).time) / 1000)
            } catch (e: java.lang.Exception) {
                (-1).toLong()
            }
        }
    }

fun timeParser(timelapse : Long = 0) : TimeContainer
{
    val timelapseTrans = max(timelapse, 0)
    val day = floor(timelapseTrans.toDouble() / (60 * 60 * 24))
    val hour = floor((timelapseTrans - day * 60 * 60 * 24) / (60 * 60))
    val minute = floor((timelapseTrans - day * 60 * 60 * 24 - hour * 60 * 60) / 60)
    val second = timelapseTrans - day * 60 * 60 * 24 - hour * 60 * 60 - minute * 60
    val dayString = String.format("%.0f", day)
    val hourString = String.format("%.0f", hour)
    val minuteString = String.format("%.0f", minute)
    val secondString = String.format("%.0f", second)
    return TimeContainer(dayString, hourString, minuteString, secondString)
}


//fun timeParser(timelapse : Long = 0) : TimeContainer{
//    val day = floor(timelapse.toDouble() / (60 * 60 * 24))
//    val hour = floor((timelapse - day * 60 * 60 * 24) / (60 * 60))
//    val minute = floor((timelapse - day * 60 * 60 * 24 - hour * 60 * 60) / 60)
//    val second = timelapse - day * 60 * 60 * 24 - hour * 60 * 60 - minute * 60
//    val dayString = String.format("%.0f", day)
//    val hourString = String.format("%.0f", hour)
//    val minuteString = String.format("%.0f", minute)
//    val secondString = String.format("%.0f", second)
//    return TimeContainer(dayString, hourString, minuteString, secondString)
//}

//ZoneId zone = ZoneId.of("America/Chicago");
//
//Instant today = Instant.parse("2018-07-18T17:15:00Z");
//Instant expiration1 = Instant.parse("2018-07-19T05:00:00Z");
//
//ZonedDateTime todayInZone = today.atZone(zone);
//ZonedDateTime expirationInZone = expiration1.atZone(zone);
//long daysTilExp = todayInZone.toLocalDate().until(expirationInZone, ChronoUnit.DAYS);
//System.out.format("From %s to %s is %d day/s.%n",
//todayInZone, expirationInZone, daysTilExp);


//String 포맷 두시간의 차이를 비교하는 함수
fun dataGapBetweenTwoDate(input_typeB: String, input_typeA: String): Array<String?> {
    val serverTimeDate = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
    return try {
        val standardTimeDateformat = serverTimeDate.parse(input_typeA)
        val comparingTimeDateformat = serverTimeDate.parse(input_typeB)
        val standardTimeDouble = standardTimeDateformat.time.toDouble()
        val comparingTimeDouble = comparingTimeDateformat.time.toDouble()
        val timediffer = (comparingTimeDouble - standardTimeDouble) / 1000
        val day = floor(timediffer / (60 * 60 * 24))
        val hour = floor((timediffer - day * 60 * 60 * 24) / (60 * 60))
        val minute = floor((timediffer - day * 60 * 60 * 24 - hour * 60 * 60) / 60)
        val second = timediffer - day * 60 * 60 * 24 - hour * 60 * 60 - minute * 60
        val dayString = String.format("%.0f", day)
        val hourString = String.format("%.0f", hour)
        val minuteString = String.format("%.0f", minute)
        val secondString = String.format("%.0f", second)
        //        tv_timeleft.setText(String.valueOf("남은시간 : " + day_string + "일" +   hour_string + "시간" +    minute_string + "분" +   second_string + "초" + "  "));
        val timearray = arrayOfNulls<String>(4)
        timearray[0] = dayString
        timearray[1] = hourString
        timearray[2] = minuteString
        timearray[3] = secondString
        timearray
    } catch (e: ParseException) {
        val timearray = arrayOfNulls<String>(0)
        timearray[0] = "error occur"
        timearray
    }
}

@SuppressLint("SimpleDateFormat")
fun nowDateString(timeZone : String = "Asia/Seoul",  DateFormat : String = "yyyy-MM-dd HH:mm:ss") : String {
    val dateString: String
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        dateString =  ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern(DateFormat))
        //LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYMMddHHmmss"))
    } else {
        val date = Date()
        val formatter = SimpleDateFormat(DateFormat)
        formatter.timeZone = TimeZone.getTimeZone(timeZone)
        dateString = formatter.format(date).toString()
    }
    return dateString
}

fun nowDate(timeZone : String = "Asia/Seoul") : Date =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant())
    } else {
        Calendar.getInstance().time
    }



//
//@SuppressLint("SimpleDateFormat")
//fun generateRandAdder(filename : String = "",
//                      extension : String = "",
//                      timezone : String = "Asia/Seoul",
//                      timeformat : String = "yyyyMMddHHmmss") : String
//{
//    val dateTransed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val postingTimeLocaldatetime = LocalDateTime.ofInstant(Date().toInstant(), ZoneId.of(timezone))
//        DateTimeFormatter.ofPattern(timeformat).format(postingTimeLocaldatetime)
//    } else {
//        val formatter = SimpleDateFormat(timeformat)
//        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
//        formatter.format(Date())
//    }
//    //시간_위치_변경수.확장자 가 이미지 이름이 된다.
//    return if(extension != "") "${dateTransed}_${filename}.${extension}"
//    else "${dateTransed}_${filename}"
//}


fun generateRandAdderRenewal(
        userFid : String,
        filename : String = "",
        extension : String = "",
        timezone : String = "Asia/Seoul",
        timeformat : String = "yyyyMMddHHmmss") : String
{
    val dateTransed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val postingTimeLocaldatetime = LocalDateTime.ofInstant(Date().toInstant(), ZoneId.of(timezone))
        DateTimeFormatter.ofPattern(timeformat).format(postingTimeLocaldatetime)
    } else {
        val formatter = SimpleDateFormat(timeformat)
        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        formatter.format(Date())
    }
    //시간_위치_변경수.확장자 가 이미지 이름이 된다.
    return if(extension != "") "${userFid}_${dateTransed}_${filename}.${extension}"
    else "${userFid}_${dateTransed}_${filename}"
}





data class ParsedDate(val year: String, val month: String, val day: String, val hour: String, val min: String)


fun intdateParsing(intdate: String) {   //     0123456789 10 11
    fun intdate_parsing(intdate: String): ParsedDate {   //     0123456789 10 11
        //예 : 201907032033f
        val intdate_string = intdate.toString()
        val year = intdate_string.substring(0, 4)
        val month = intdate_string.substring(4, 6)
        val day = intdate_string.substring(6, 8)
        val hour = intdate_string.substring(8, 10)
        val min = intdate_string.substring(10, 12)
        return ParsedDate(year, month, day, hour, min)
    }

}




fun dateParsing(input_type: String?): DateParsingInfo {
    val transdatefrom = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val Basicformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = SimpleDateFormat("yyyy/MM/dd") //   System.out.println("오늘 날짜는 " + date.format(today));  -> 2006/10/03
    val time = SimpleDateFormat("hh:mm ss a") //   System.out.println("현재 시간은 " + time.format(today));  -> 04:22 15 오후
    //SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");        String sCertDate = dateFormat.format(new Date());
    val simpleYear = SimpleDateFormat("YY")
    val simpleMonth = SimpleDateFormat("MM")
    val simpleDay = SimpleDateFormat("dd")
    val simpleHour = SimpleDateFormat("hh")
    val simpleMinute = SimpleDateFormat("mm")
    val simpleSecond = SimpleDateFormat("ss")
    val simpleDaynight = SimpleDateFormat("a")
    var year: String? = ""
    var month: String? = ""
    var day: String? = ""
    var hour: String? = ""
    var minute: String? = ""
    var second: String? = ""
    var daynight: String? = ""
    try {
        year = simpleYear.format(transdatefrom.parse(input_type))
        month = simpleMonth.format(transdatefrom.parse(input_type))
        day = simpleDay.format(transdatefrom.parse(input_type))
        hour = simpleHour.format(transdatefrom.parse(input_type))
        minute = simpleMinute.format(transdatefrom.parse(input_type))
        second = simpleSecond.format(transdatefrom.parse(input_type))
        daynight = simpleDaynight.format(transdatefrom.parse(input_type))
    } catch (e: Exception) {
        println(e.message)
    }
    return DateParsingInfo(year, month, day, hour, minute, second, daynight)
}


class DateParsingInfo(var year: String?, var month: String?, var day: String?, var hour: String?, var minute: String?, var second: String?, var daynight: String?) {

}