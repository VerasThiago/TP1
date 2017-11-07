package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.find;

public class FindController extends Controller {
    public static Result find() {

        return ok(find.render("Chora viado"));

    }
}
