enum Mood { SAD, HAPPY, OK }

class MoodHandler{
  static var moodMap =  {Mood.SAD : "SAD", Mood.HAPPY : "HAPPY", Mood.OK : "OK"};

  static String moodString(Mood mood){
    return moodMap[mood] as String;
  }

}