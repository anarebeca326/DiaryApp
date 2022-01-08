import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_diary_app/icon_selector.dart';
import 'package:flutter_diary_app/modify_note.dart';
import 'models/note.dart';

class DetailsPage extends StatefulWidget {
  final Note note;
  final Function modifyNote;
  final int index;

  const DetailsPage({Key? key, required this.note, required this.modifyNote, required this.index}) : super(key: key);

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
          actions: <Widget>[
            Padding(
                padding: const EdgeInsets.only(right: 20.0),
                child: GestureDetector(
                  onTap: () {
                    Navigator.push( context, MaterialPageRoute(
                        builder: (context) => ModifyNotePage(modifyNote: widget.modifyNote, note: widget.note, index: widget.index)
                    ));
                  },
                  child: const Icon(
                    Icons.edit,
                  ),
                )
            ),
          ]
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