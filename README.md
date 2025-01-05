Thinking in Epoxy

When we use Epoxy, we are likely to think of every view as an individual component and called EpoxyModel. 
An EpoxyModel can be used inside any EpoxyController.
![image](https://github.com/user-attachments/assets/3fa71612-8c20-4ba3-88fb-67706147b115)

his is how it should look like inside EpoxyController.

One of the best things that Epoxy provides is that we are allowed to write for-loop and if-else inside buildModels method. 
The logic that we write reflects what we will get.
![image](https://github.com/user-attachments/assets/533bc2eb-2249-45c0-b551-90dc6187274b)

Reusability
ViewHolder is independent, but whenever you want to use a ViewHolder inside any adapters
you will have to register
it manually inside those adapters by yourself. This is what Epoxy has solved by isolating 
all those components into each and individual.
So every model in Epoxy is independent so it can be used in every controller.
For example, a simple loading indicator can be used throughout the application.

Epoxy Models
Epoxy Model is an individual component that we can use inside any controller.
It’s quite similar to a RecyclerView ViewHolder however, 
it gives some advanced features to work with such as view visibility spans and much more. 
There are two approaches to create Epoxy Models.

The most common one is to use create an abstract class and annotate it with @EpoxyModelClass(..) to let it
generate the implementation class for us. The generated classes will be suffixed with _ (underscore). For example, 
we have an abstract model class call CardModel so the generated class is CardModel_ . 
One thing to note is that if you are using Kotlin, 
it will generate DSL extension functions that you can easily use inside any controllers.

The second method is to create a non-abstract class that extends EpoxyModel and overrides all those abstract methods by ourselves. 
This approach can be difficult since we have to implement methods such as getDefaultLayout() and createNewHolder() manually.
class MyCardModel: EpoxyModelWithHolder<CardModel.CardHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.component_card
    }
    
    override fun createNewHolder(parent: ViewParent): CardModel.CardHolder {
        return CardModel.CardHolder()
    }
}
Create an EpoxyHolder
It’s basically the same as RecyclerView ViewHolder but this one is for Epoxy.

class CardHolder : EpoxyHolder() {
      lateinit var binding: ComponentCardBinding
          private set
      override fun bindView(itemView: View) {
          binding = ComponentCardBinding.bind(itemView)
      }
  }
  The method bindView(itemView: View) is called when the layout has been inflated for the first time. 
  The method will be called once, so we can use something like findViewById(...) to create a reference to our view,
  or in this example, I bound the ViewBinding inside that method.

Create a Model from EpoxyHolder
Start by creating an abstract class that is annotated with 
@EpoxyModelClass(layout = R.layout.<layout_name>) 
also extended with 
EpoxyModelWithHolder<YourHolder>()

Epoxy Controllers
A high-level adapter for RecyclerView. Models can only be used inside controllers,
so let’s start building a controller for our models.
EpoxyAsyncUtil.getAsyncBackgroundHandler() is used to tell Epoxy that 
we want any diffing and building process
to be done in a dedicated background thread instead of the UI thread.
It significantly improves the rendering performance of our application.
We then just put our generated DSL models inside buildModel() method accordingly.
We can use Loop, If-Else inside this method.
