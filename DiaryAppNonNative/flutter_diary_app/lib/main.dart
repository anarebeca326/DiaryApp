import 'dart:collection';

import 'package:flutter/material.dart';

import 'assets/app_icons.dart';
import 'models/note.dart';

void main() {
  runApp(const MaterialApp(home: MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);


  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    List<Note> notes = [ Note("O zi minunata", "description", "HAPPY", 1),
      Note("O zi mai putin minunata", "description", "SAD", 3),
      Note("O zi", "description", "OK", 2),
      Note("Nu mai stiu ce titluri sa dau", "description", "HAPPY", 1),
      Note("O alta zi", "description", "SAD", 3),
      Note("Ultima zi probabil", "description", "OK", 2)];

    var iconMap =  {1: AppIcons.happy, 2: AppIcons.neutral, 3: AppIcons.sad};

    return MaterialApp(
      title: 'My Diary Notes',
      home: Scaffold(
          appBar: AppBar(
            title: const Text("My Diary Notes"),
            backgroundColor: Colors.pink,
              actions: <Widget>[
          Padding(
          padding: const EdgeInsets.only(right: 20.0),
          child: GestureDetector(
            onTap: () {
              testAlert(context);
            },
            child: const Icon(
              Icons.add,
            ),
          )
      ),
          ]),
          body: ListView.builder
            (
              itemCount: notes.length,
              itemBuilder: (BuildContext context, int index) {
                return  ListTile(
                    leading: Icon(
                      iconMap[notes[index].icon]
                    ),
                    title: Text(notes[index].title),
                    subtitle: Text(notes[index].mood));
              }
          )
      )
    );
  }

  void testAlert(BuildContext context) {
    var alert = const AlertDialog(
      title: Text("Alert"),
      content: Text("Add not yet implemented :D"),
    );

    showDialog(
        context: context,
        builder: (BuildContext context) {
          return alert;
        });
  }

}