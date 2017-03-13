package com.rent.mytestapplication.homework;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Crea
 * ted by Administrator on 2017/3/13.
 */
public class Delegate {

    @Test
    public void test() {
        Subscriber subscriber = new Subscriber();
        subscriber.add(new PlayGames(), "hideGames");
        subscriber.add(new WatchTV(), "close", "open the Book");
        subscriber.notifyEvents();
    }

    class PlayGames {
        PlayGames() {
            System.out.println("I'm playing games");
        }

        void hideGames() {
            System.out.println("I have hided games");
        }
    }

    class WatchTV {
        WatchTV() {
            System.out.println("I'm watching TV");
        }

        void close(String doSomething) {
            System.out.println("I have closed TV, and " + doSomething);
        }
    }

    class Subscriber {
        EventDelegate delegate;

        Subscriber() {
            delegate = new EventDelegate();
        }

        Subscriber add(Object obj, String method, Object... params) {
            delegate.add(new Event(obj, method, params));
            return this;
        }

        void notifyEvents() {
            delegate.notifyEvents();
        }
    }

    class EventDelegate {
        List<Event> events;

        EventDelegate() {
            this.events = new ArrayList<>();
        }

        EventDelegate add(Event event) {
            events.add(event);
            return this;
        }

        void notifyEvents() {
            for (Event event : events) {
                event.invoke();
            }
        }
    }

    class Event {
        Object obj;
        String methodName;
        Object[] params;
        Class[] parameterTypes;

        Event(Object obj, String method, Object[] params) {
            this.obj = obj;
            this.methodName = method;
            this.params = params;
            if (params != null && params.length > 0) {
                parameterTypes = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    parameterTypes[i] = params[i].getClass();
                }
            }
        }

        void invoke() {
            try {
                Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
                if (method != null) {
                    method.invoke(obj, params);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
