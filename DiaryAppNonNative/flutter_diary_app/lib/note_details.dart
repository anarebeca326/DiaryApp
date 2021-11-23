import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_diary_app/icon_selector.dart';
import 'models/note.dart';

class DetailsPage extends StatefulWidget {
  final Note note;

  const DetailsPage({Key? key, required this.note}) : super(key: key);

  @override
  State<DetailsPage> createState() => _DetailsPageState();
}

class _DetailsPageState extends State<DetailsPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: <Widget>[
            Text(
                widget.note.title + "  ",
                style: const TextStyle(fontSize: 30)),
            Icon(IconSelector.getIcon(widget.note.icon))
            ],
          ),
        backgroundColor: Colors.pink,
      ),
      body:
          ListView(
              padding: const EdgeInsets.all(40.0),
              children: <Widget>[
                Text(
                    "Mood: " + widget.note.mood,
                    style: const TextStyle(fontSize: 30)),
                const Text("", style: TextStyle(height: 4)),
                Text(widget.note.description, style: const TextStyle(fontSize: 22))
              ]
          )
    );
  }
}