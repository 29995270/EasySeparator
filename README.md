# EasySeparator
A very easy way to create simple item separator for recycler view.
-----
![](https://github.com/29995270/EasySeparator/blob/master/art.gif "recyclerview")
```java
recyclerView.addItemDecoration(
        new EasySeparator.Builder(this)
                .color(R.color.colorAccent)
                .decorationHeight(8)
                .decorationStartMargin(0)
                .decorationEndMargin(40)
                .orientation(LinearLayoutManager.VERTICAL)
                .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                    @Override
                    public boolean shouldDrawDecoration(int viewType) {
                        return viewType == Adapter.TYPE_B;
                    }
                })
                .build()
);
```
```java
recyclerView.addItemDecoration(
        new EasySeparator.Builder(this)
                .drawable(R.drawable.separator_1)
                .decorationHeight(20)
                .decorationStartMargin(0)
                .decorationEndMargin(0)
                .orientation(LinearLayoutManager.VERTICAL)
                .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                    @Override
                    public boolean shouldDrawDecoration(int viewType) {
                        return viewType == Adapter.TYPE_C;
                    }
                })
                .build()
);
```
Features
--------
* separator can be a pure colorful line, drawable, vector drawable or multi type separator in one item type
* orientation : vertical or horizental (only work for LinearLayoutManager now)
* separator margin (start or end) and height
* draw deferent separator for deferent item type

Usage
--------
just copy <a href="https://github.com/29995270/EasySeparator/blob/master/app/src/main/java/com/wq/freeze/easyseparatorexample/EasySeparator.java">EasySeparator.java</a> to your project and enjoy it.