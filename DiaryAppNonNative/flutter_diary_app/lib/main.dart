import 'package:flutter/material.dart';
import 'package:flutter_diary_app/add_note.dart';
import 'package:flutter_diary_app/icon_selector.dart';
import 'faker.dart';
import 'models/note.dart';
import 'note_details.dart';

void main() {
  runApp(const MaterialApp(home: MyApp()));
}


class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'My Diary Notes',
      home: HomePage(),
    );
  }
}


class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);
  @override
  _HomePageState createState() => _HomePageState();
}


class _HomePageState extends State<HomePage> {

  List<Note> notes = [ Note("O zi minunata", Faker.generateShortText(), "HAPPY", 1),
    Note("La padure", Faker.generateShortText(), "SAD", 3),
    Note("O zi", Faker.generateShortText(), "OK", 2),
    Note("Alt titlu", Faker.generateLongText(), "HAPPY", 1),
    Note("O alta zi", Faker.generateLongText(), "SAD", 3),
    Note("Plimb catei", Faker.generateLongText(), "OK", 2)];


  void _addNote(Note note) {
    setState(() {
      notes.add(note);
    });
  }

  void _modifyNote(Note note, int index){
    setState(() {
      notes[index] = note;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: createAppBar(context),
        body: createMainListView(context)
    );
  }


  AppBar createAppBar(BuildContext context){
    return AppBar(
        title: const Text("My Diary Notes"),
        backgroundColor: Colors.pink,
        actions: <Widget>[
          Padding(
              padding: const EdgeInsets.only(right: 20.0),
              child: GestureDetector(
                onTap: () {
                  Navigator.push( context, MaterialPageRoute(
                      builder: (context) => AddNotePage(addNote: _addNote)
                  ));
                },
                child: const Icon(
                  Icons.add,
                ),
              )
          ),
        ]);
  }

  ListView createMainListView(BuildContext context){
    return ListView.builder
      (
        itemCount: notes.length,
        itemBuilder: (BuildContext context, int index) {
          return Dismissible(
              key: UniqueKey(),
              direction: DismissDirection.endToStart,
              onDismissed: (_){
                setState(() {
                  notes.removeAt(index);
                });
              },
              confirmDismiss: (DismissDirection direction) async {
                return await showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return AlertDialog(
                          title: const Text("Confirm"),
                          content: const Text("Are you sure you wish to delete this item?"),
                          actions: <Widget>[
                            TextButton(
                                onPressed: () => Navigator.of(context).pop(true),
                                child: const Text("DELETE")
                            ),
                            TextButton(
                              onPressed: () => Navigator.of(context).pop(false),
                              child: const Text("CANCEL"),
                            )]);
                    });
              },
              child: ListTile(
                leading: Icon(
                    IconSelector.getIcon(notes[index].icon)),
                title: Text(notes[index].title),
                subtitle: Text(notes[index].mood),
                onTap: () {
                  Navigator.push( context, MaterialPageRoute(
                      builder: (context) => DetailsPage(note: notes[index], modifyNote: _modifyNote, index: index)
                  ));
                },
              ),

              background: Container(
                  color: Colors.red,
                  margin: const EdgeInsets.symmetric(horizontal: 15),
                  alignment: Alignment.centerRight,
                  child: const Icon(
                    Icons.delete,
                    color: Colors.white,
                  )));
        });
  }


  void testAlert(BuildContext context) {
    var alert = const AlertDialog(
      title: Text("Alert"),
      content: Text("Add not yet implemented"),
    );

    showDialog(
        context: context,
        builder: (BuildContext context) {
          return alert;
        });
  }
}