package com.example.kike.recipeslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Space;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> mRecipes;
    private RecyclerView listRecipes;
    private RecipeAdapter mRecipeAdapter;
    private Space space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRecipes = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(this);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        listRecipes.setLayoutManager(mLinearLayout);
        space = findViewById(R.id.space);

        fillRecipes();
        initAdapter();
    }

    private void initAdapter() {
        mRecipeAdapter = new RecipeAdapter(mRecipes);
        listRecipes.setAdapter(mRecipeAdapter);
    }

    public void fillRecipes() {
        mRecipes = new ArrayList<>();
        mRecipes.add(new Recipe("Lobster Ravioli and Shrimp",
                "This is not your typical ravioli. To take the tender pasta pillows to the next level, we filled them with succulent lobster meat, paired them with sweet sautéed shrimp, and tossed them in a lemony dill cream sauce. For an elevated finish (and delicious crunch), we tangled marinated zucchini ribbons on top.",
                "INGREDIENTS: \n" +
                        "- 1 unit\n" +
                        "- Lemon\n" +
                        "- ¼ ounce\n" +
                        "- Dill\n" +
                        "- 10 ounce\n" +
                        "- Shrimp\n" +
                        "- 1 unit\n" +
                        "- Zucchini\n" +
                        "- 9 ounce\n" +
                        "- Lobster Ravioli\n" +
                        "- 1 unit\n" +
                        "- Vegetable Stock Concentrate\n" +
                        "- 2 tablespoon\n" +
                        "- Sour Cream",
                R.drawable.recipe1));


        mRecipes.add(new Recipe("Quick Beef Ragù Spaghetti",
                "Spaghetti with tomato sauce is always a recipe for success. Is there anyone who’d turn down a plate of noodles piled high and dusted with cheese? Surely not, although adding some extra meat and veg certainly makes it all the more crave-worthy. And that’s exactly what we’re doing in this version: taking a tried-and-true Italian American classic and filling it out with hearty ground beef and bites of tender zucchini.",
                "INGREDIENTS: \n" +
                        "- 2 unit\n" +
                        "- Zucchini\n" +
                        "- 2 unit\n" +
                        "- Yellow Onion\n" +
                        "- 4 clove\n" +
                        "- Garlic\n" +
                        "- ¼ ounce\n" +
                        "- Thyme\n" +
                        "- 20 ounce\n" +
                        "- Ground Beef\n" +
                        "- 1 tablespoon\n" +
                        "- Italian Seasoning\n" +
                        "- 4 tablespoon\n" +
                        "- Soy Sauce\n" +
                        "- 12 ounce\n" +
                        "- Spaghetti\n" +
                        "- 26.4 ounce\n" +
                        "- Crushed Tomatoes\n" +
                        "- ½ cup\n" +
                        "- Parmesan Cheese\n" +
                        "- 1 teaspoon\n" +
                        "- Chili Flakes",
                R.drawable.recipe2));

        mRecipes.add(new Recipe("Crab Cakes Piccata",
                "This recipe takes pasta night to the next level, elevating noodles into something that’s unlike anything anyone has seen before. Fresh tagliatelle is coated in a savory sauce, then nestled on the plate next to crab cakes seared to golden brown perfection. Some capers are folded in too, earning this dish the name piccata. It’s all finished with a sprinkle of tomato and breadcrumbs to give it the right amount of crispy texture and oomph.",
                "INGREDIENTS: \n" +
                        "- 1 unit\n" +
                        "- Lemon\n" +
                        "- ½ unit\n" +
                        "- Shallot\n" +
                        "- 2 clove\n" +
                        "- Garlic\n" +
                        "- ¼ ounce\n" +
                        "- Parsley\n" +
                        "- 1 unit\n" +
                        "- Roma Tomato\n" +
                        "- ¼ cup\n" +
                        "- Panko Breadcrumbs\n" +
                        "- 10 ounce\n" +
                        "- Crab Cakes\n" +
                        "- 9 ounce\n" +
                        "- Tagliatelle Pasta\n" +
                        "- 1 teaspoon\n" +
                        "- Flour\n" +
                        "- 1 unit\n" +
                        "- Chicken Stock Concentrate\n" +
                        "- 1 ounce\n" +
                        "- Capers\n" +
                        "- 1 teaspoon\n" +
                        "- Chili Flakes",
                R.drawable.recipe3));

        mRecipes.add(new Recipe("Maple-Glazed Pork Chops",
                "Dinnertime usually means savory, but there are some amazing ways to make it sweet. Like by creating a glaze out of maple syrup, as we’ve done here on these pork chops. It gives you a little bit of breakfast-y comfort while staying perfectly hearty. Pop some cinnamon sweet potatoes and buttery green beans on the side, and you’ve got the best of all worlds.",
                "INGREDIENTS: \n" +
                        "- 2 unit\n" +
                        "- Sweet Potato\n" +
                        "- 12 ounce\n" +
                        "- Pork Chops\n" +
                        "- 1 clove\n" +
                        "- Garlic\n" +
                        "- 6 ounce\n" +
                        "- Green Beans\n" +
                        "- ½ teaspoon\n" +
                        "- Cinnamon\n" +
                        "- 1 ounce\n" +
                        "- Maple Syrup\n" +
                        "- 1.5 tablespoon\n" +
                        " -Balsamic Vinegar\n" +
                        " -1 ounce\n" +
                        " -Pecans\n",
                R.drawable.recipe4));
    }


}
