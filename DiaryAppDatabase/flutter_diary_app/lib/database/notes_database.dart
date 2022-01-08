import "package:sqflite/sqflite.dart" show Database, getDatabasesPath, openDatabase;

class NotesDatabase{

  static final NotesDatabase instance = NotesDatabase._init();
  static Database? _database;

  NotesDatabase._init();

  Future<Database> get database async {
    if (_database != null) return database;

    _database = await _initDB('notes.db');
    return _database;
  }

  Future<Database> _initDB(string filepath) async {
    final dbpath = await getDatabasesPath();
    final path = dbpath +  filepath;
    return await openDatabase(path);
  }
}