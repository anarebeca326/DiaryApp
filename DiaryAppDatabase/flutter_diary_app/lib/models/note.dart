
class Note{
  int? id;
  late String title;
  late String description;
  late String mood;
  late int icon;

  Note(this.title, this.description, this.mood, this.icon);

  Map<String, Object?> toJson() => {
    "id" : id,
    "title" : title,
    "description" : description,
    "mood" : mood,
    "icon" : icon,
  };

  Note copy(int id, Note note) {
    note.id = id;
    return note;
  }

  static Note fromJson(Map<String, Object?> json) {
    Note note = Note(json["title"] as String, json["description"] as String,
        json["mood"] as String, json["icon"] as int);
    note.id = json["id"] as int;
    return note;
  }
}