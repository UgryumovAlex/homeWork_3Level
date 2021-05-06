public class ClassForTesting {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Запускаем метод Before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Запускаем метод After Suite");
    }

    @Test(priority = 10)
    public void test1() {
        System.out.println("this is test1");
    }

    @Test(priority = 8)
    public void test2() {
        System.out.println("this is test2");
    }

    @Test(priority = 6)
    public void test3() {
        System.out.println("this is test3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("this is test4");
    }

    @Test(priority = 4)
    public void test5() {
        System.out.println("this is test5");
    }

    @Test(priority = 5)
    public void test10() {
        System.out.println("this is test10");
    }
}
