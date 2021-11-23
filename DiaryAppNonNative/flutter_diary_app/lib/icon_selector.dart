import 'package:flutter/cupertino.dart';

import 'assets/app_icons.dart';

class IconSelector{
  static var iconMap =  {1: AppIcons.happy, 2: AppIcons.neutral, 3: AppIcons.sad};

  static IconData? getIcon(int iconID){
    return iconMap[iconID];
  }
}