/**The CookBook class keeps track of a set of Recipes that is organized by Recipe's compareTo method, and provides methods to alter recipes and get recipes based on their data
* @author Rebecca Kuhlman
* Collaborators: n/a
* Teacher Name: Ishman
* Period: 2
* Due Date: 05-18-20
 */
 import java.util.ArrayList;
 import java.util.TreeSet;

public class CookBook
{
  
  private String name;
  private TreeSet<Recipe> recipes;

  /*Creates a CookBook object and resets TreeSet
    @param name Name of the CookBook
  */
  public CookBook(String name)
  {
    this.name = name;
    recipes = new TreeSet<>();
  }

  /*Returns the recipe list
    @return recipe list
  */
  public TreeSet<Recipe> getRecipes()
  {
    return recipes;
  }

  /*Returns the name of the CookBook
    @return name of the CookBook
  */
  public String getName()
  {
    return name;
  }
  
  /*Returns a set of recipes for the given meal type 
    @param mealType to get (Breakfast, Lunch, Dinner, Dessert)
    @return set of recipes for the mealType
  */
  public TreeSet<Recipe> getMealTimeRecipes(String mealType)
  {
    TreeSet<Recipe> meals = new TreeSet<>();
    for(Recipe rep : recipes)
    {
      if(rep.equals(mealType))
        meals.add(rep);
    }
    return meals;
  }
  
  /*Adds a recipe to the recipe list
    @param rep Recipe to be added
    @return whether or not the add was sucessful
  */
  public boolean addRecipe(Recipe rep)
  {
    return recipes.add(rep);
  }
  
  /*Removes a recipe to the recipe list
    @param rep Recipe to be removed
    @return whether or not the remove was sucessful
  */
  public boolean removeRecipe(Recipe rep)
  {
    return recipes.remove(rep);
  }
  
  /*Returns a random meal from the recipe list of a given mealType
    @param mealType to get a recipe for (Breakfast, Lunch, Dinner, Dessert)
    @return random recipe, null if no recipes of that mealType
  */
  public Recipe getRandomMeal(String mealType)
  {
    if(recipes == null)
      return null;
    TreeSet<Recipe> meals = getMealTimeRecipes(mealType);
    if(meals == null)
      return null;
    int rando = (int)(Math.random() * meals.size());
    return (Recipe)(meals.toArray())[rando];
  }
  
  /*Compiles a list of Vegan Recipes of a given mealType
    @param mealType to get a recipe for (Breakfast, Lunch, Dinner, Dessert)
    @return set of vegan recipes, null if no recipes of that mealType are vegan
  */
  public TreeSet<Recipe> getVeganMenu(String mealType)
  {
    TreeSet<Recipe> veganFood = new TreeSet<>();
    for(Recipe rep : recipes)
    {
      if(rep.ifVegan() && rep.getMealType().equals(mealType))
        veganFood.add(rep);
    }
    return veganFood;
  }
  
  /*Finds and returns the recipe of a given mealType that has the shortest make time
    @param mealType to get a recipe for (Breakfast, Lunch, Dinner, Dessert)
    @return recipe with the shortest cook time of that mealType
  */
  public Recipe getShortestCookTime(String mealType)
  {
    if(recipes == null)
      return null;
    Recipe shorty = null;
    for(Recipe rep : recipes)
    {
      if(shorty == null || (rep.getMealType().equals(mealType) && shorty.getCookTime() >= rep.getCookTime()))
        shorty = rep;
    }
    return shorty;
  }
  
  /*Given a max time and a mealType, returns a set of recipes that are on or below the maximum getShortCookTimes 
    @param mealType to get a recipe for (Breakfast, Lunch, Dinner, Dessert)
    @param max Maximum cooktime (in minutes) for the recipes
    @return set of recipes fitting params, empty if none found
  */
  public TreeSet<Recipe> getShortCookTimes(String mealType, int max)
  {
    TreeSet<Recipe> shorts = new TreeSet<>();
    for(Recipe rep : recipes)
    {
      if(rep.getMealType().equals(mealType) && rep.getCookTime() <= max)
        shorts.add(rep);
    }
    return shorts;
  }
  
  /*Compiles a set of recipes of a given dish type (pies, soups, breads, etc.)
    @param typeOfDish to find
    @return set of recipes of the dish type
  */
  public TreeSet<Recipe> getDishesOfType(String typeOfDish)
  {
    TreeSet<Recipe> dishes = new TreeSet<>();
    for(Recipe rep : recipes)
    {
      if(rep.getTypeOfDish().equals(typeOfDish))
        dishes.add(rep);
    }
    return dishes;
  }
  
  /*Makes a given recipe vegan (no animal products) using Recipe method
    @param Recipe to make vegan 
  */
  public void makeVegan(Recipe rep)
  {
    for(Recipe check : recipes)
    {
      if(rep.equals(check))
        check.makeVegan();
    }
  }
  
  /*Makes all recipes in the CookBook vegan (no animal products) using Recipe method
  */
  public void veganCookBook()
  {
    for(Recipe rep : recipes)
    {
      if(!rep.isVegan())
      {
        rep.makeVegan();
      }
    }
  }
  
  /*Formats the data in the CookBook class to a String
    @return formatted string
  */
  public String toString()
  {
    String all = name + "";
    boolean firstBFast = false;
    boolean firstLunch = false;
    boolean firstDinner = false;
    boolean firstDessert = false;
    for(Recipe rep : recipes)
    {
      String mealType = rep.getMealType();
      if(!firstBFast && mealType.equals(Recipe.TYPE_BFAST))
      {
        firstBFast = true;
        all += "\n ~~Breakfast~~ \n";
      }
      else if(!firstLunch && mealType.equals(Recipe.TYPE_LUNCH))
      {
        firstLunch = true;
        all += "\n ~~Lunch~~ \n";
      }
      else if(!firstDinner && mealType.equals(Recipe.TYPE_DINNER))
      {
        firstDinner = true;
        all += "\n ~~Dinner~ \n";
      }
      else if(!firstDessert && mealType.equals(Recipe.TYPE_DESSERT))
      {
        firstDessert = true;
        all += "\n ~~Dessert~~ \n";
      }
      rep.toString();
    }
    return all;
  }
  
}

