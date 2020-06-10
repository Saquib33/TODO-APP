package comm.example.controller;
import comm.example.model.Todo;
import comm.example.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @RequestMapping("/listTodo")
    public String listTodo(Model model) {
        model.addAttribute("todos", todoService.getTodos());
        return "todo-list";

    }
    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model model)
    {
        model.addAttribute("todo",new Todo());
        return "todo-form";
    }
    @RequestMapping("/saveTodo")
    public String saveTodo(@ModelAttribute("todo") Todo todo)
    {
        todoService.createTodo(todo);
        return "redirect:/todo/listTodo";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("todoId") int theId,
                                    Model theModel) {

        Todo theTodo = todoService.getTodo(theId);
        theModel.addAttribute("todo", theTodo);

        return "todo-form";
    }
    @RequestMapping("/updateTodo")
    public String updateTodo(@ModelAttribute("todo") Todo todo)
    {
        todoService.createTodo(todo);
        return "redirect:/todo/listTodo";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("todoId") int todoId)
    {
        todoService.deleteTodo(todoId);
        return "redirect:/todo/listTodo";
    }
}
