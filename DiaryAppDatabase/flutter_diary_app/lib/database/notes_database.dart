import 'package:flutter_diary_app/models/note.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class NotesDatabase {
  static final NotesDatabase instance = NotesDatabase._init();
  static Database? _database;

  NotesDatabase._init();

  Future<Database?> get database async {
    if (_database != null) return _database;

    _database = await _initDB('notes.db');

    return _database!;
  }

  Future<Database> _initDB (String filePath) async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, filePath);
    return await openDatabase(path, version: 1, onCreate: _createDB);
  }

  Future _createDB(Database db, int version) async{
    await db.execute(
        '''

                  CREATE TABLE notes( 
                        id integer primary key autoincrement,
                        title text not null,
                        description text not null,
                        mood text text not null,
                        icon integer not null
                    )
                
        '''
    );
  }

  Future<Note> create(Note note) async{
    final db = await instance.database;
    final id = await db!.insert("notes", note.toJson());

    return note.copy(id, note);
  }

  Future<Note?> read(int id) async {
    final db = await instance.database;

    final maps = await db!.query(
        "notes",
        columns: ["id", "title", "description", "mood", "icon"],
        where: 'id = ?',
        whereArgs: [id],
    );

    if (maps.isNotEmpty) {
      return Note.fromJson(maps.first);
    } else {
      return null;
    }
  }

  Future<List<Note>> readAll() async {
    final db = await instance.database;
    final result = await db!.query("notes");
    return result.map((json) => Note.fromJson(json)).toList();
  }

  Future<int> update(Note note) async {
    final db = await instance.database;

    return db!.update(
      "notes",
      note.toJson(),
      where: 'id = ?',
      whereArgs: [note.id],
    );
  }

  Future<int> delete(int id) async {
    final db = await instance.database;

    return await db!.delete(
      "notes",
      where: 'id = ?',
      whereArgs: [id],
    );
  }

  Future<int> deleteTitle(String title) async {
    final db = await instance.database;

    return await db!.delete(
      "notes",
      where: 'title = ?',
      whereArgs: [title],
    );
  }

  Future close() async {
    final db = await instance.database;
    db!.close();
  }




}
