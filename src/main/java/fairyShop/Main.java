package fairyShop;

import fairyShop.core.Engine;
import fairyShop.core.EngineImpl;
import fairyShop.models.BaseHelper;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
        BaseHelper pl = new BaseHelper() {
        }
    }
}
