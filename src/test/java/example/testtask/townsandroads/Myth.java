//package example.testtask.townsandroads;
//
//import java.util.HashMap;
//import java.util.Map;
///**
// * Класс, опровергающий факт о том, что реализациии интерфейса Map
// * не могут содержать неуникальные объекты ключей.
// * Также демонстрирует Set, стандартная реализация которого содержит
// * неуникальные объекты.
// */
//public class Myth {
//
//    private String dataMember;
//
//    public void setDataMember(String dataMember) {
//        this.dataMember = dataMember;
//    }
//
//    public Myth(String dataMember) {
//        this.dataMember = dataMember;
//    }
//
//    // любезно сгенерированный Eclipse
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((dataMember == null) ? 0 : dataMember.hashCode());
//        return result;
//    }
//
//    // любезно сгенерированный Eclipse
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        Myth other = (Myth) obj;
//        if (dataMember == null) {
//            if (other.dataMember != null) return false;
//        } else if (!dataMember.equals(other.dataMember))
//            return false;
//        return true;
//    }
//
//    /**
//     * Метод печатает в стандартный поток вывода информацию
//     * о равенстве передаваемых объектов.
//     */
//    static void comparePrint(final Object obj1, final Object obj2) {
//        System.out.println("Comparing by \"equals\"      is " + obj1.equals(obj2));
//        System.out.println("Comparing by \"hashCode\" is " + (obj1.hashCode() == obj2.hashCode()));
//    }
//
//    /**
//     * Метод печатает в стандартный поток вывода информацию
//     * о количестве объектов, содержащихся в Map.
//     */
//    static void mapSizePrint(final Map map) {
//        System.out.println("Map size is " + map.size());
//    }
//
//    public static void main(String[] args) {
//        // создание объектов ключей
//        final Myth key1 = new Myth("hoho");
//        final Myth key2 = new Myth("haha");
//
//        // печатаем в консоли данные о сравнении объектов
//        comparePrint(key1, key2);    // Comparing by "equals"      is false
//        // Comparing by "hashCode"  is false
//
//        // создание объекта Map, содержащего объекты с очень
//        // важной информацией ;)
//        final Map<Myth,Object> map = new HashMap<Myth,Object>();
//        map.put(key1, "Very important data 1");
//        map.put(key2, "Very important data 2");
//
//        mapSizePrint(map);    // Map size is 2
//
//        // по велению кривых рук или плохого дизайна программы
//        // изменяем объект второго ключа на идентичный первому
//        key2.setDataMember("hoho");
//
//        // как видно из напечатанного два объекта логически идентичны
//        comparePrint(key1, key2);    // Comparing by "equals"      is true
//        // Comparing by "hashCode"  is true
//
//        // при этом размерность экземпляра Map не поменялась:
//        mapSizePrint(map);    // Map size is 2
//
//        // проверяем Set на содержание неуникальных объектов
//        int i = 0;
//        for (final Myth key : map.keySet()) {
//            // как видно Set содержит 2 абсолютно идентичных объекта
//            System.out.println("The " + ++i + " key object is " + key);
//        }
//        // The 1 key object is test.Myth@30f46d
//        // The 2 key object is test.Myth@30f46d
//
//        // пытаемся получить значение по первому ключу
//        System.out.println(map.get(key1)); // Very important data 1
//
//        // пытаемся получить значение по второму ключу
//        System.out.println(map.get(key2)); // Very important data 1
//    }
//}
