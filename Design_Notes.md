Why you used ArrayList instead of array ?

Mostly in this application ArrayList is used because of its dynamic nature. We never know how many items the user will add to the list, so using an ArrayList allows us to easily add and remove items without worrying about the underlying array size. Additionally, ArrayList provides built-in methods for common operations like adding, removing, and searching for items, which simplifies our code and improves readability.


Where you used static members and why?

In Repository section I used static keyword for the list of itmes it holds because I want to make sure that all instances of the Repository class share the same list of items. This way, when one instance adds or removes an item from the list, it will be reflected across all instances of the Repository class. Using static members allows us to maintain a single shared state for the list of items, which is essential for our application to function correctly. Also in Main.java you will find the static keyword used along with final for calling service methods as and when user submitted the input for application . As we are using main method which is static , so any non static variable or method cannot be called inside main method without creating an instance of the class. By declaring the service variable as static, we can directly call its methods without needing to create an instance of the class, which simplifies our code and allows us to easily access the service methods from the main method.


Where you used inheritance and what you gained from it?

In this application , I used inheritance on the Student and Course classes to create a more organized and maintainable code structure. By using inheritance, I was able to define common properties and methods in a base class and then extend them in the derived classes. This allowed me to reduce code duplication and make it easier to manage and update the application in the future.