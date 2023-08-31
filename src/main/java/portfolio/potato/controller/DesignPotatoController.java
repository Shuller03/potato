package portfolio.potato.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import portfolio.potato.logic.IngredientRepository;
import portfolio.potato.model.Ingredient;
import portfolio.potato.model.Ingredient.Type;
import portfolio.potato.model.Potato;
import portfolio.potato.model.PotatoOrder;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("potatoOrder")
public class DesignPotatoController {

    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignPotatoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "potatoOrder")
    public PotatoOrder order() {
        return new PotatoOrder();
    }

    @ModelAttribute(name = "potato")
    public Potato taco() {
        return new Potato();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredients.forEach(ingredientList::add);
        return ingredientList
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(
            @Valid Potato potato, Errors errors,
            @ModelAttribute PotatoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addPotato(potato);
        log.info("Processing potato: {}", potato);
        return "redirect:/orders/current";
    }
}