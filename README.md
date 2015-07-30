JCGE (JAVA COLLABORATIVE GAME ENGINE)
===================

*I'm not a native english speaker and there will be probably a lot of grammar mistakes. I will try my best to make it fully understandable for now and review/correct it later.*

JCGE is an open source project which, as the name indicates, is a game engine using java 8.

Everybody who feels like he can bring somethings to the project is free to make a pull request and eventually see his code pushed to the dev branch. In order to be able to do this, I will describe you briefly the engine structure, its lifecycle as which is for now decided and is not going to change (the implementation can still be changed at this early stage of refactoring), as well as the modules used and how to use them (which can still be discussed).

Then I will talk about what are the objectives and the priorities for now and will explain the workflow of the contributors.

----------

JCGE objectives
-------------

#### Personnal objectives

The main objective for the people working on this project is too deepen their knowledge and improve their programming skills.

You should not considere this more seriously than something which makes you able to get experience on a subject you are interested into.


#### Project objectives
About the engine itself, the original goal was to make an engine which allowed users to easilly set up their game by just having to focus on the logic of their own game and the grapchis. This would be accomplished by giving them all the other tools they need to be able to make the game they want, regardless its complexity.

To be able to do this, we need to decouple or code to be able to easily give several implementations of the engine depending on the user's needs. This means a clean, well documented and tested code which respects the design patterns we chose.

Regarding the feature we want to offer, it's really up to contributors imagination. I myself got some ideas which already should give us enough work for the next monthes but you are free to suggest/pull request new features as long as you respect the project architecture and patterns.

----------


JCGE architecture
-------------

I do not have the time to represent it with graphs and diagrams for now but basially, the architecture of the core of the project could be represented with a tree.

Each level of the tree contains the level below itself and a reference to the level above if it exists.
So it would look like this  (I'm omitting the managers which are containers for several objects and encapsulate the logic associated to those objects. ie: ```SceneManager```, which contains several scenes, is the class that will be exposed to ```Game``` instead of just having a list of ```Scene``` objects ) :

> - ```Game```
>- ```Scene```
>- ```GameObject```
>- ```Component```/```Script```


#### Game

This is the class which contains the main and the game loop (it's fine if you do not know what a game loop is for now, I will explain this in the Lifecycle part). It is the highest level of the architecture.

#### Scene

This class is a collection of GameObjects to be loaded for a specific part of the game, such as a level in Mario. The name is really questionable as it gives it a stronger connotation than what it actually is, simply a collection of game object. For now the game is supposed to load only one collection of GameObject at the time, but this can be discussed later.

#### GameObject

This class represents any object of the game. It is a really standard concept that you can find among a lot of game engines as it allows every object of the game to follow the lifecycle of the engine easily and to give consistency to your way of doing things, and consistency is always good!
GameObjects differ with the components and the scripts that they hold.

#### Component

This class is a reusable set of properties for GameObjects. If you want to define your GameObject's structure, such as its position, its physical caracteristics, etc... You do this by creating components which hold those values and give them to the object you want. As to how to define the logic which initialize and changes those values, we're going to leave them to the scripts.

#### Script

This class defines the logic behind the GameObject and more accurately, behind the value of the component fields. One could wonder why script are not below components in the architecture. This is because one script can actually have an impact on several components and then we would have a hard time to decide on which component we would like to attach such a script.

During the first stage of the development, we decided to have 3 different types of script for our needs.
>- ```LoadingScript``` - Called once when the GameObject is loaded (usefull to initialize components)
>- ```ListenerScript``` - Called upon an event
>- ```UpdateScript``` - Called everytime the game Update its state (see lifecycle)


----------


JCGE lifecycle
-------------

As I said earlier, we are using a gameloop which is the ```Game``` class and this piece of code is the one in charge to run the game. Basically, it will update every GameObjects as much as it can while rendering the game 60 times per second.
I found it on a forum ait works wonders, but if you want to challenge yourself and improve it, you're more than welcome to do so!

So I just mentionned the 2 method called by the gameloop in order to run the game but before this, we need to get some initialisation going. For this, we decided to have 2 methods which are  ```init(Parent p); ``` and  ```load(); ```

Do you remember when I said that scripts can change the values of components fields? Well, first of all this is not the only interaction between different objects in the engine and then we need to enable such interactions to happen by giving the two objects a way to communicate so scripts can get and set values from components.

For now, I've only found two way to do this, giving each objects a reference to its parent so in the end every objects can communicate, or event based communication. We went for the first one because it is way easier to implement but nothing stops us to implement the latter later on.

When we run the application, the first thing which is done is the creation of the required objects (The  ```new Game() ```, the  ```new Scene() ```, then we populate the scene with several  ```new GameObject()```, etc... ). Each constructor is empty so we can freely instantiate objects without having to include them in the architecture right away by having to give it its parent and created the ```init(Parent p); ``` method for this.

#### init(Parent p);

This is the first method called in the lifecycle of the application. If it's still unclear for you, you can think about the tree structure I was talking about earlier. Before calling ```init(Parent p); ``` , we just have all the elements randomly disposed on the ground since we got no idea of under which branch we should place any of the elements we got. When called, ```init(Parent p); ``` will tell each element who is its parent so we are able to get the tree structure.

#### load();

This second method's job is to give an object the references to other objects it needs to do its job.
*TODO : ADD CODE EXAMPLE*
As we use the fact that each element know both its sons and parent, we need to be sure that **every elements** in the tree called their ```init(Parent p); ``` method or we might have ```NullPointerException``` thrown at runtime.


----------


JCGE modules
-------------

Up until now, we've talked about the core of the engine. The engines hopefully contains more feature (sound, graphics, network...) and we will see here how those are supposed to be plugged to the core.

I have been through a lot of reading when I was working on the project a few monthes ago and I came across [this awesome website](http://gameprogrammingpatterns.com/) which I recommend you want to go deeper or if you are just curious, and there is a chapter about the [service locator pattern](http://gameprogrammingpatterns.com/service-locator.html) which is the one I chose to use for the project.

Even though it is not really implemented this way yet, the idea is to create public APIs for each kind of modules we got and to make them available from one place which is our singleton service locator.

It gives us the advantage to be sure that everybody is using the same service everywhere in the code regardless of the implementation which is being used.


----------


JCGE dependencies
-------------

> - JUnit - For the unit tests, discussion is opened if you know better frameworks as we will probably need some complex tests that I doubt JUnit supports natively such as asynchronous tests.
> - json - A Json Parsing library which allows to easilly retrive java Objects from Json files.
> - log4j2 - This is the librarywe will be using to log the engine but I did not manage to make it work yet.


----------


How to contribute
-------------

Please, only contribute to the project if you're ready to respect the following.

When contributing to the project, one should always ask himself what is the best way of accomplishing what he wants to accomplish. The idea is that once the refactor is done, we keep ourselves in a healthy development environment with fully documented and tested code which respects today's known best practices.

Before contributing, one should check the [public scrumboard](https://trello.com/b/abL2lFNZ/scrumboard) to know what needs to be done, what's already being worked on by the main team and what's done.

*Be aware that the main team is suceptible to start a story on which you were working on. If it happens, pull request the dev branch as soon as possible with an explicit pull request name so we can take your work in consideration.*

#### How to use the scrumboard

![](http://puu.sh/jjhts/18461a80ea.png)

As we are using a scrumb board, we are also going to use the same terminologies.

As the product owner, I will be defining features which will be represented wih labels. Features will be most of the time a part of the project that we want to deliver which is big enough for us to split it into several stories. Stories are represented by trello cards so we can sort them by label/feature. Stories are then split again in something close to atomic operatons which are called tasks. Tasks are represtend through the checklist of a trello card. 

Several contributors can work on the same story but only one can own it. If you want to work on someone's story, you can feel free to discuss with him on how you could share the tasks. If somebody else than the owner works on a task, edit the checklist to *Mention Member*.

![](http://puu.sh/jjiw9/8ae3412503.png)

This system should be more than enough for us to be able to organize our work, the only condition is that everybody update their cards accordingly.

I am the product owner but you are free to challenge my ideas or to suggest new things into the product through the [discussion board](https://trello.com/b/xPC8Qn9I/discussion). This board is private to the main team and every member is welcome to discuss anything regarding the project here.

#### Pull request

For now, I will be the only one able to merge pull requests into the repo's branches. This will not be the case forever, but this will last until the main team gets used to the project.

You will have to fork the repository and to code on your local branch, then once you are ready to commit you will form a pull request to the dev branch.

There are some conditions for a pull request to be accepted which also defines our "definition of done" (or what's needed to be done to be able to move a story/trello card in the done column) :

> - The code respects the architecture and the patterns chosen for the project
> - The code is fully documented in english
> - The code is tested

Also, you can try to add the url of the story in trello in your commit comment so it is easier for other people to track your contribution.

Some exceptions might be made for bug fixes.

----------


Development environment
-------------

This part concerns the main team

You will need a [github](https://github.com/join) and [trello](https://trello.com/signup) account.

For the IDE, I suggest you use the [Intellij community edition](https://www.jetbrains.com/idea/download/). Its code analasys tools are really helpful to improve and optimize our code.
If you have [git](https://git-scm.com/download/win) installed, you can easilly set up your version control to bind your github account into Intellij.

----------

If you have any question about what has been said here, feel free to contact me at pascal.luttgens@hotmail.fr
