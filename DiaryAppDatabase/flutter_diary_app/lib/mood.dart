enum Mood { SAD, HAPPY, OK }

class MoodHandler{
  static var moodMap =  {Mood.SAD : "SAD", Mood.HAPPY : "HAPPY", Mood.OK : "OK"};
  static var stringMap =  {"SAD" : Mood.SAD, "HAPPY" : Mood.HAPPY,"OK" :  Mood.OK};

  static String moodString(Mood mood){
    return moodMap[mood] as String;
  }

  static Mood getMoodFromString(String moodString){
    return stringMap[moodString] as Mood;
  }

}