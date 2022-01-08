import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'icon_selector.dart';
import 'models/note.dart';
import 'mood.dart';

class AddNotePage extends StatefulWidget {

  final Function addNote;
  const AddNotePage({Key? key, required this.addNote}) : super(key: key);

  @override
  State<AddNotePage> createState() => _AddNotePageState();

}

class _AddNotePageState extends State<AddNotePage>{

  String _titleInput = '';
  String _descriptionInput = '';
  Mood? _mood = Mood.OK;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: const <Widget>[
            Text(
                "Create a new note",
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
                decoration: const InputDecoration(
                  border: UnderlineInputBorder(),
                  hintText: 'Title...'
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
                decoration: const InputDecoration(
                    border: UnderlineInputBorder(),
                    hintText: 'Description...'
                )),
            const SizedBox(
              height: 20,
            ),
            const SizedBox(
              height: 20,
            ),
            ElevatedButton(
                onPressed: () => {
                widget.addNote(Note(_titleInput, _descriptionInput, MoodHandler.moodString(_mood as Mood), IconSelector.getIconIdFromMood(MoodHandler.moodString(_mood as Mood)))),
                Navigator.of(context).pop(),
                },
                child: const Text("ADD")
            )
          ]
      )
    );
  }
}