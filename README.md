Tsumugi
=======

サロゲートの扱いを、少し簡単にします。

<img src="./image/sea.png" alt="Sea" width="144"/>
<img src="./image/sea-struct.png" alt="Sea" width="320"/>

<img src="./image/sea-vs.png" alt="Sea VS" width="144"/>
<img src="./image/sea-vs-struct.png" alt="Sea VS" width="320"/>

How to use
----------
### Source ###

    public class Example {

        private static String EXAMPLE_STRING = "芦田さんは芦\uDB40\uDD01屋のお嬢様だ";

        public static void main(String[] args) {

            // UniCharSequence Sample
            System.out.println(UniCharSequence.class.getSimpleName());
            final UniCharSequence uniCharSequence = new UniCharSequence(EXAMPLE_STRING);
            System.out.println(uniCharSequence.length());// 13
            System.out.println(uniCharSequence.toCharList().stream().map(uniChar -> uniChar.toString()).collect(Collectors.joining(", ")));
            // 芦, 田, さ, ん, は, 芦, 󠄁, 屋, の, お, 嬢, 様, だ,

            // OneCharSequence Sample
            System.out.println(OneCharSequence.class.getSimpleName());
            final OneCharSequence oneCharSequence = new OneCharSequence(new UniCharSequence(EXAMPLE_STRING));
            System.out.println(oneCharSequence.length());// 12
            System.out.println(oneCharSequence.toCharList().stream().map(oneChar -> oneChar.toString()).collect(Collectors.joining(", ")));
            // 芦, 田, さ, ん, は, 芦󠄁, 屋, の, お, 嬢, 様, だ,
        }
    }

Methods
-------

つJavadoc

Environment
-----------

	gradle 4.6
	java version "9.0.4"

Build
-----

	gradle build

Release History
---------------

+ **v1.0.0-b01** - 2018-04-08
   + Java 9 対応
+ **v0.2.0** - 2017-12-09
   + 保守
+ **v0.1.0** - 2015-08-30
   + 最初のコミット

License
-------
Copyright &copy; 2015-2018 [hiro20v](https://github.com/hiro20v)  
Distributed under the [MIT License][mit].

[MIT]: http://opensource.org/licenses/MIT
