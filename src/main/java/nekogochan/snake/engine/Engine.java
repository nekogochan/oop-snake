package nekogochan.snake.engine;

import static java.lang.Math.*;
import static java.lang.System.*;

public abstract class Engine {

    abstract protected void handleInput();

    abstract protected void update();

    abstract protected void render();

    protected boolean running = false;
    protected int updatesPerSecond = 4;
    protected int rendersPerSecond = 4;
    protected long updateTs = 0;
    protected long renderTs = 0;

    private int updateDt = 1000 / updatesPerSecond;
    private int renderDt = 1000 / rendersPerSecond;

    private static long ts() {
        return currentTimeMillis();
    }

    private void sync() {
        var ts = ts();
        var updateDts = updateTs - ts;
        var renderDts = renderTs - ts;
        var minDts = min(updateDts, renderDts);
        if (minDts > 0) {
            try {
                Thread.sleep(minDts);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void start() {
        running = true;
        while (running) {
            out.println("Engine.start.running");
            var ts = ts();

            if (updateTs < ts) {
                out.println("should update");
                handleInput();
                update();
                updateTs = ts + updateDt;
            }

            if (renderTs < ts) {
                out.println("should render");
                render();
                renderTs = ts + renderDt;
            }

            sync();
        }
    }

    public void stop() {
        running = false;
    }
}
