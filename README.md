# JCTRNN

Implements a continuous time recurrent neural network (CTRNN)[1] in Java. Layouts, connections and parameters can be specified as a JSON file, which is parsed into a network layout. Specific parameters can be set, creating a high performance CTRNN object which integrates each step.

Depends on the [DWLP](https://github.com/mbryantlibrary/dlwp) project.

This was a library I first developed in my [Artificial Life project](https://github.com/mbryantlibrary/PreMCA). I recognised the potential for reuse, and extracted a general purpose library. The code is based on C code written by [Randall Beer](http://mypage.iu.edu/~rdbeer/) who pioneered Artificial Life/neural network type research in the 90s.

The core `CTRNN` class is written for speed using arrays; this is necessary because in the evolutionary algorithm experiments I was running thousands of networks are simulated (profiling shows that the core `step` function could be called about 20m times in a typical evolutionary run).

To make this class easier to modify, which is necessary for evolutionary experiments, a `CTRNNLayout` class was created, which builds a network with an easier interface, based on the Java Collections framework. This could be a kind of `Builder` pattern, I suppose, as it generates a `CTRNN` object through the method `createCTRNN()`.

To make my life even easier, I decided to allow the user to specify network layouts in a JSON file, which could then be dynamically loaded to simplify testing different networks. My original code for translating JSON into `CTRNNLayout` is [here](https://github.com/mbryantlibrary/PreMCA/blob/master/src/org/mb459/easy/premca/sim/ctrnn/CTRNNLayout.java)(scroll to bottom); its horrendous, messy and bug-prone, so I rewrote from scratch for a more recent project using this. I'm much prouder of the [new code](https://github.com/mbryantlibrary/jctrnn/blob/master/src/main/java/me/mb/jctrnn/json/Network.java), even if it is poorly documented; I'd recently read [Refactoring - Martin Fowler] (http://www.amazon.co.uk/Refactoring-Improving-Design-Existing-Technology/dp/0201485672) and put some of these patterns to use.

This library has served me well and performed without need for much modification. If I were to make it a more general purpose and optimised library for distribution, I'd probably add the option to have custom neural net activation functions (through a callback interface, e.g. `Activation Function`). This would also allow the interchangeable use of lookup tables for the expensive calculations of `Math.tanh(x)` to improve speed at the expense of accuracy.

[1] Beer, R.D. (1995). On the dynamics of small continuous-time recurrent neural networks.
