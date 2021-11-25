import 'package:flutter/cupertino.dart';

import 'assets/app_icons.dart';

class IconSelector{
  static var iconMapInt =  {1: AppIcons.happy, 2: AppIcons.neutral, 3: AppIcons.sad};
  static var iconMapReverseInt = {AppIcons.happy : 1, AppIcons.neutral : 2, AppIcons.sad : 3};
  static var iconMapString = {"SAD": AppIcons.sad, "HAPPY": AppIcons.happy, "OK": AppIcons.neutral};

  static Type nonNullableTypeOf<T>(T? object) => T;

  static IconData? getIcon(int iconID){
    return iconMapInt[iconID];
  }

  static IconData? getIconByMood(String iconStr){
    return iconMapString[iconStr];
  }

  static int getIconIdFromMood(String mood){
    return  iconMapReverseInt[iconMapString[mood]] as int;
  }
}