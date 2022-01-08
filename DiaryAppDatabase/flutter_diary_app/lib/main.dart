import 'package:flutter/material.dart';
import 'package:flutter_diary_app/add_note.dart';
import 'package:flutter_diary_app/database/notes_database.dart';
import 'package:flutter_diary_app/icon_selector.dart';
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

  List<Note> notes = [];


  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: createAppBar(context),
        //body: createMainListView(context)
      body: createFutureListView(context)
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

  FutureBuilder<List> createFutureListView(BuildContext context){
    return FutureBuilder<List>(
      future: NotesDatabase.instance.readAll(),
      initialData: const [],
      builder: (context, snapshot) {
        return snapshot.hasData
            ? ListView.builder(
          itemCount: snapshot.data!.length,
          itemBuilder: (BuildContext context, int index) {
            return Dismissible(
                key: UniqueKey(),
                direction: DismissDirection.endToStart,
                onDismissed: (_){
                  _deleteNote(snapshot.data![index].id);
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
                      IconSelector.getIcon(snapshot.data![index].icon)),
                  title: Text(snapshot.data![index].title),
                  subtitle: Text(snapshot.data![index].mood),
                  onTap: () {
                    Navigator.push( context, MaterialPageRoute(
                        builder: (context) => DetailsPage(note: snapshot.data![index], modifyNote: _modifyNote, index: index)
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
          },
        )
            : const Center(
          child: CircularProgressIndicator(),
        );
      },
    );
  }


  Future refreshList() async {
    notes = await NotesDatabase.instance.readAll();
  }

  Future _addNote(Note note) async {
    await NotesDatabase.instance.create(note);
    setState(() {
      refreshList();
    });
  }

  Future _modifyNote(Note note, int index) async{
    note.id = notes[index].id;
    await NotesDatabase.instance.update(note);
    setState(() {
      refreshList();
    });
  }

  Future _deleteNote(int? id) async{
    await NotesDatabase.instance.delete(id!);
    setState(() {
      refreshList();
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