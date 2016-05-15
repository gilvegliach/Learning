package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Entry;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class EntryController extends Controller {
  public Result getEntries() {
    return ok(Json.toJson(Entry.find.all()));
  }

  public Result getEntry(String id) {
    Entry entry = Entry.find.byId(id);
    if (entry == null) {
      return notFound("Entry with id " + id + " is not found");
    }
    return ok(Json.toJson(entry));
  }

  public Result postEntry() {
    JsonNode jsonNode = request().body().asJson();
    Entry entry = Json.fromJson(jsonNode, Entry.class);
    entry.save();

    return created(Json.toJson(entry))
        .withHeader(LOCATION, "/entries/" + entry.id);
  }

  public Result deleteEntry(String id) {
    Entry.find.deleteById(id);
    return ok();
  }
}
