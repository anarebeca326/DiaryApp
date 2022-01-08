import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'icon_selector.dart';
import 'models/note.dart';
import 'mood.dart';

class ModifyNotePage extends StatefulWidget {

  final Function modifyNote;
  final Note note;
  final int index;
  const ModifyNotePage({Key? key, required this.modifyNote, required this.note, required this.index}) : super(key: key);

  @override
  State<ModifyNotePage> createState() => _ModifyNotePageState(note.mood);

}

class _ModifyNotePageState extends State<ModifyNotePage>{

  String _titleInput = '';
  String _descriptionInput = '';
  Mood? _mood;

  _ModifyNotePageState(String mood) {
    _mood = MoodHandler.getMoodFromString(mood);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Row(
            children: const <Widget>[
              Text(
                  "Edit note",
                  style: TextStyle(fontSize: 30))
            ],
          ),
          backgroundColor: Colors.pink,
        ),
        body: ListView(
            padding: const EdgeInsets.only(left: 60.0, right: 60.0),
            children: <Widget> [
              const SizedBox(
                height: 20,
              ),
              TextField(
                  onChanged: (newText) {
                    _titleInput = newText;
                  },
                  decoration: InputDecoration(
                      border: const UnderlineInputBorder(),
                      hintText: widget.note.title,
                  )),
              const SizedBox(
                height: 20,
              ),
              Column(
                children: <Widget>[
                  ListTile(
                    title: const Text('HAPPY'),
                    leading: Radio(
                      value: Mood.HAPPY,
                      groupValue: _mood,
                      onChanged: (Mood? value) {
                        setState(() {
                          _mood = value;
                        });
                      },
                    ),
                  ),
                  ListTile(
                    title: const Text('OK'),
                    leading: Radio(
                      value: Mood.OK,
                      groupValue: _mood,
                      onChanged: (Mood? value) {
                        setState(() {
                          _mood = value;
                        });
                      },
                    ),
                  ),
                  ListTile(
                    title: const Text('SAD'),
                    leading: Radio(
                      value: Mood.SAD,
                      groupValue: _mood,
                      onChanged: (Mood? value) {
                        setState(() {
                          _mood = value;
                        });
                      },
                    ),
                  ),
                ],
              ),
              const SizedBox(
                height: 20,
              ),
              TextField(
                  onChanged: (newText) {
                    _descriptionInput = newText;
                  },
                  maxLines: 8,
                  decoration: InputDecoration(
                      border: const UnderlineInputBorder(),
                      hintText: widget.note.description,
                  )),
              const SizedBox(
                height: 20,
              ),
              const SizedBox(
                height: 20,
              ),
              ElevatedButton(
                  onPressed: () => {
                    widget.modifyNote(Note(_titleInput != "" ? _titleInput : widget.note.title,
                        _descriptionInput != "" ? _descriptionInput : widget.note.description,
                        MoodHandler.moodString(_mood as Mood),
                        IconSelector.getIconIdFromMood(MoodHandler.moodString(_mood as Mood))), widget.index),
                    Navigator.of(context).pop(),
                    Navigator.of(context).pop(),
                  },
                  child: const Text("SAVE CHANGES")
              )
            ]
        )
    );
  }
}