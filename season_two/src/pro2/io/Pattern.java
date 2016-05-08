package pro2.io;

import org.junit.Test;

/**
 * 装饰设计模式：
 * Created by Near on 2015/12/3.
 */
public class Pattern {
    @Test
    public void test(){
        Voice voice = new Voice();
        voice.say();
        Amplifier amplifier = new Amplifier(voice);
        amplifier.say();
    }
}

/**
 * 对 Voice 类进行装饰
 */
class Amplifier{
    private Voice voice;

    public Amplifier() {}

    public Amplifier(Voice voice) {
        this.voice = voice;
    }

    public void say(){
        this.voice.setSound(this.voice.getSound()*10);
        System.out.println("Sound: " + this.voice.getSound());
    }
}

class Voice{
    private int sound = 10;

    public Voice() {}

    public Voice(int sound) {
        this.sound = sound;
    }

    public int getSound() {

        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public void say(){
        System.out.println("Sound: "+sound);
    }
}
