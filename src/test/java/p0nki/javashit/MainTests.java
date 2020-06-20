package p0nki.javashit;

import org.junit.Test;
import p0nki.javashit.ast.IndentedLogger;
import p0nki.javashit.ast.JSASTCreator;
import p0nki.javashit.ast.JSParseException;
import p0nki.javashit.ast.nodes.JSASTNode;
import p0nki.javashit.builtins.Builtins;
import p0nki.javashit.run.JSContext;
import p0nki.javashit.run.JSEvalException;
import p0nki.javashit.token.JSTokenList;
import p0nki.javashit.token.JSTokenizeException;
import p0nki.javashit.token.JSTokenizer;

import java.util.HashMap;

public class MainTests {

    private void tokenize(String str) throws JSTokenizeException {
        System.out.println(str);
        JSTokenizer tokenizer = new JSTokenizer();
        JSTokenList tokens = tokenizer.tokenize(str);
        for (int i = 0; i < tokens.getSize(); i++) {
            System.out.println(tokens.get(i));
        }
    }

    private void ast(JSContext ctx, String str) throws JSTokenizeException, JSParseException, JSEvalException {
        JSTokenizer tokenizer = new JSTokenizer();
        JSTokenList tokens = tokenizer.tokenize(str);
        JSASTCreator astCreator = new JSASTCreator();
        JSASTNode node = astCreator.parseExpression(tokens);
        node.print(new IndentedLogger());
        System.out.println("<- " + str);
        System.out.println("-> " + node.evaluate(ctx));
//        ctx.keys().forEach(key -> {
//            try {
//                System.out.println("KEY " + key + " = " + ctx.get(key).stringify());
//            } catch (JSReferenceException e) {
//                e.printStackTrace();
//            }
//        });
        System.out.println();
    }

    @Test
    public void test() throws JSEvalException, JSTokenizeException, JSParseException {
        JSContext ctx = new JSContext(null, new HashMap<>());
        ctx.set("println", Builtins.PRINTLN);
        ctx.set("dir", Builtins.DIR);
        ctx.set("Math", Builtins.MATH);

//        ast(ctx, "Math");
//        ast(ctx, "Math.min(5, 10, -3)");
//        ast(ctx, "!true ^ false");
//        ast(ctx, "!false ^ false");
//        ast(ctx, "!(false & false)");
//        ast(ctx, "!false & true");
//        ast(ctx, "5 +- 3 +- 4");

//        ast(ctx, "function(x) { println(2+x) } (4)");
//        ast(ctx, "add = function(x,y) { return x + y }");
//        ast(ctx, "add(5,4)");

        ast(ctx, "Math.any(true, false)");
        ast(ctx, "Math.any(false, false)");
        ast(ctx, "Math.all(true, false)");
        ast(ctx, "Math.all(true, true)");

        ast(ctx, "Math.any([false, true])");
        ast(ctx, "Math.min([3, 4, 5])");

//        ast(ctx, "i=5");
//        ast(ctx, "pure = function( ) { let i = 3 return i }");
//        ast(ctx, "pure()");
//        ast(ctx, "i");
//        ast(ctx, "impure = function(){i=3 return i}");
//        ast(ctx, "impure()");
//        ast(ctx, "i");
//        ast(ctx, "my_object = {func: function(a) { return 3 + a * 5}}");
//        ast(ctx, "my_object.func(5)");

//        ast(ctx, "Math");
//        ast(ctx, "f = function() {println(arguments)}");
//        ast(ctx, "f(3, 4)");
//        ast(ctx, "f()");

//        ast(ctx, "a\nb = 5");
//        ast(ctx, "a\nb * a\nb");

//        ast(ctx, "5 < 4");
//        ast(ctx, "5 < 4 ^ 4 < 5");
//        ast(ctx, "4 < 5");
//        ast(ctx, "x = 4 < 5");
//        ast(ctx, "y = 5 < 4");
//        ast(ctx, "x");
//        ast(ctx, "y");
//        ast(ctx, "x & y");
//        ast(ctx, "return 5");

//        ast(ctx, "i = 5");
//        ast(ctx, "i=i+1");
//        ast(ctx, "i");

//        ast(ctx, "dir(\"test\")");
//        ast(ctx, "dir([5, 4])");
//        ast(ctx, "dir({x: 5, y: 4})");
//        ast(ctx, "dir(dir)");

//        ast(ctx, "z = {increment: function() { this.x = this.x + 1 }, x: 4}");
//        ast(ctx, "z");
//        ast(ctx, "z.increment()");
//        ast(ctx, "z");

//        ast(ctx, "for(i=1;i<5;i=i+1){println(i)}");
//        ast(ctx, "if(5 > 4) { println(true) }");

        ast(ctx, "factorial = function(x) { if(x>1){return x*factorial(x-1)}else{return 1}}");
        ast(ctx, "for(let i=1;i<11;i=i+1){println(i, factorial(i))}");

//        ast(ctx, "throw { x : 5 }");

//        ast(ctx, "i = 5");
//        ast(ctx, "f = function(i) { }");
//        ast(ctx, "f(10)");
//        ast(ctx, "i");

//        ast(ctx, "try { throw Math.random() } catch(exc) { println(exc) }");
//        ast(ctx, "i = 1");
//        ast(ctx, "try { throw Math.random() } catch (i) { i = 5 println(i) }");
//        ast(ctx, "i");
//        ast(ctx, "try { throw 5 } catch (exc) { println(\"ISSUE \" + exc) }");

//        ast(ctx, "f = function(x){return function(y){return x+y}}");
//        ast(ctx, "f");
//        ast(ctx, "f(3)");
//        ast(ctx, "z=f(3)");
//        ast(ctx, "z(5)");

//        ast(ctx, "my_class = function(initCounter) { return { counter:initCounter, increment: function() {this.counter=this.counter+1}, decrement: function() {this.counter=this.counter-1}, get: function(){return this.counter}, set: function(newValue){this.counter=newValue}}}");
//        ast(ctx, "my_class(5)");
//        ast(ctx, "obj = my_class(5)");
//        ast(ctx, "obj.increment()");
//        ast(ctx, "obj.get()");
//        ast(ctx, "obj.set(10)");
//        ast(ctx, "obj.get()");

//        ast(ctx, "arr = [5, 4, {x: 1, y: 2}]");
//        ast(ctx, "foreach(value:arr){println(value)}");
//        ast(ctx, "foreach(value,index:arr){println(index+\" \"+value)}");

        // TODO: `global` (with a new token type) object which represents the global ctx
        // TODO: value listeners which listen for a value change
//        ast(ctx, "z = {\"x\": 3, y: 4}");
//        ast(ctx, "z.ten = {test: 10}");
//        ast(ctx, "z[\"ten\"]");
//        ast(ctx, "z[\"t\"+\"en\"]");
//        ast(ctx, "z[\"a key which doesn't exist\"]");
    }

}
