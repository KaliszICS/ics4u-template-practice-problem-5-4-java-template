import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

class JUnitWithReflectionTest {

    // Helper methods for reflection
    private Class<?> getClassSafely(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private Constructor<?> getConstructorSafely(Class<?> clazz, Class<?>... paramTypes) {
        if (clazz == null) return null;
        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Method getMethodSafely(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        if (clazz == null) return null;
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Object invokeMethodSafely(Object obj, Method method, Object... args) {
        if (method == null || obj == null) return null;
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            return null;
        }
    }

    private Object createInstanceSafely(Constructor<?> constructor, Object... args) {
        if (constructor == null) return null;
        try {
            return constructor.newInstance(args);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean implementsInterface(Class<?> clazz, String interfaceName) {
        if (clazz == null) return false;
        Class<?> interfaceClass = getClassSafely(interfaceName);
        if (interfaceClass == null) return false;
        
        return Arrays.stream(clazz.getInterfaces())
                     .anyMatch(i -> i.equals(interfaceClass));
    }

    // Tests for Player interface existence
    @Test
    @DisplayName("Test Player interface exists")
    void playerInterfaceExistsTest() {
        Class<?> playerInterface = getClassSafely("Player");
        assertNotNull(playerInterface, "Player interface should exist");
        assertTrue(playerInterface.isInterface(), "Player should be an interface");
    }

    @Test
    @DisplayName("Test Player interface methods")
    void playerInterfaceMethodsTest() {
        Class<?> playerInterface = getClassSafely("Player");
        if (playerInterface == null) {
            fail("Player interface not found");
            return;
        }
        
        assertNotNull(getMethodSafely(playerInterface, "start"), "Player interface should have start method");
        assertNotNull(getMethodSafely(playerInterface, "stop"), "Player interface should have stop method");
        assertNotNull(getMethodSafely(playerInterface, "volumeUp"), "Player interface should have volumeUp method");
        assertNotNull(getMethodSafely(playerInterface, "volumeDown"), "Player interface should have volumeDown method");
    }

    // Tests for VideoPlayer class
    @Test
    @DisplayName("Test VideoPlayer class implements Player interface")
    void videoPlayerImplementsInterfaceTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        assertNotNull(videoPlayerClass, "VideoPlayer class should exist");
        assertTrue(implementsInterface(videoPlayerClass, "Player"), 
                "VideoPlayer should implement Player interface");
    }

    @Test
    @DisplayName("Test VideoPlayer constructor")
    void videoPlayerConstructorTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        assertNotNull(constructor, "VideoPlayer should have a constructor that accepts a String parameter");
    }

    @Test
    @DisplayName("Test VideoPlayer start functionality")
    void videoPlayerStartTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(videoPlayerClass, "start");
        if (startMethod == null) {
            fail("VideoPlayer should have start method");
            return;
        }
        
        invokeMethodSafely(videoPlayer, startMethod);
        
        Method getOnOffMethod = getMethodSafely(videoPlayerClass, "getOnOff");
        if (getOnOffMethod == null) {
            fail("VideoPlayer should have getOnOff method");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getOnOffMethod);
        assertEquals(Boolean.TRUE, result, "VideoPlayer should be turned on after start");
    }

    @Test
    @DisplayName("Test VideoPlayer stop method")
    void videoPlayerStopTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method stopMethod = getMethodSafely(videoPlayerClass, "stop");
        assertNotNull(stopMethod, "VideoPlayer should have stop method");
        
        invokeMethodSafely(videoPlayer, stopMethod);
        
        Method getOnOffMethod = getMethodSafely(videoPlayerClass, "getOnOff");
        if (getOnOffMethod == null) {
            fail("VideoPlayer should have getOnOff method");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getOnOffMethod);
        assertEquals(Boolean.FALSE, result, "VideoPlayer should be turned off after stop");
    }

    @Test
    @DisplayName("Test VideoPlayer volumeUp method")
    void videoPlayerVolumeUpTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method volumeUpMethod = getMethodSafely(videoPlayerClass, "volumeUp");
        assertNotNull(volumeUpMethod, "VideoPlayer should have volumeUp method");
        
        invokeMethodSafely(videoPlayer, volumeUpMethod);
        
        Method getVolumeMethod = getMethodSafely(videoPlayerClass, "getVolume");
        if (getVolumeMethod == null) {
            fail("VideoPlayer should have getVolume method");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getVolumeMethod);
        assertEquals(5, result, "VideoPlayer volume should increase by 5 after volumeUp");
        
        invokeMethodSafely(videoPlayer, volumeUpMethod);
        result = invokeMethodSafely(videoPlayer, getVolumeMethod);
        assertEquals(10, result, "VideoPlayer volume should increase by 5 after second volumeUp");
    }

    @Test
    @DisplayName("Test VideoPlayer volumeDown method")
    void videoPlayerVolumeDownTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method volumeDownMethod = getMethodSafely(videoPlayerClass, "volumeDown");
        assertNotNull(volumeDownMethod, "VideoPlayer should have volumeDown method");
        
        invokeMethodSafely(videoPlayer, volumeDownMethod);
        
        Method getVolumeMethod = getMethodSafely(videoPlayerClass, "getVolume");
        if (getVolumeMethod == null) {
            fail("VideoPlayer should have getVolume method");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getVolumeMethod);
        assertEquals(-5, result, "VideoPlayer volume should decrease by 5 after volumeDown");
        
        invokeMethodSafely(videoPlayer, volumeDownMethod);
        result = invokeMethodSafely(videoPlayer, getVolumeMethod);
        assertEquals(-10, result, "VideoPlayer volume should decrease by 5 after second volumeDown");
    }

    @Test
    @DisplayName("Test VideoPlayer fastForward method")
    void videoPlayerFastForwardTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(videoPlayerClass, "start");
        if (startMethod == null) {
            fail("VideoPlayer should have start method");
            return;
        }
        
        invokeMethodSafely(videoPlayer, startMethod);
        
        Method fastForwardMethod = getMethodSafely(videoPlayerClass, "fastForward");
        assertNotNull(fastForwardMethod, "VideoPlayer should have fastForward method");
        
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        
        Method getCurrentTimeMethod = getMethodSafely(videoPlayerClass, "getCurrentTime");
        if (getCurrentTimeMethod == null) {
            fail("VideoPlayer should have getCurrentTime method");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getCurrentTimeMethod);
        assertEquals(5, result, "VideoPlayer current time should be 5 after fastForward");
        
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        result = invokeMethodSafely(videoPlayer, getCurrentTimeMethod);
        assertEquals(15, result, "VideoPlayer current time should be 15 after three fastForwards");
    }

    @Test
    @DisplayName("Test VideoPlayer rewind method")
    void videoPlayerRewindTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(videoPlayerClass, "start");
        Method fastForwardMethod = getMethodSafely(videoPlayerClass, "fastForward");
        Method rewindMethod = getMethodSafely(videoPlayerClass, "rewind");
        Method getCurrentTimeMethod = getMethodSafely(videoPlayerClass, "getCurrentTime");
        
        if (startMethod == null || fastForwardMethod == null || 
            rewindMethod == null || getCurrentTimeMethod == null) {
            fail("VideoPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(videoPlayer, startMethod);
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        invokeMethodSafely(videoPlayer, fastForwardMethod);
        invokeMethodSafely(videoPlayer, fastForwardMethod);  // Time should be 20
        
        invokeMethodSafely(videoPlayer, rewindMethod);
        invokeMethodSafely(videoPlayer, rewindMethod);  // Time should be 10
        
        Object result = invokeMethodSafely(videoPlayer, getCurrentTimeMethod);
        assertEquals(10, result, "VideoPlayer current time should be 10 after four fastForwards and two rewinds");
    }

    @Test
    @DisplayName("Test VideoPlayer getVideo and setVideo methods")
    void videoPlayerGetSetVideoTest() {
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        Constructor<?> constructor = getConstructorSafely(videoPlayerClass, String.class);
        if (constructor == null) {
            fail("VideoPlayer constructor not found");
            return;
        }
        
        Object videoPlayer = createInstanceSafely(constructor, "Movie");
        if (videoPlayer == null) {
            fail("Failed to create VideoPlayer instance");
            return;
        }
        
        Method getVideoMethod = getMethodSafely(videoPlayerClass, "getVideo");
        Method setVideoMethod = getMethodSafely(videoPlayerClass, "setVideo", String.class);
        
        if (getVideoMethod == null || setVideoMethod == null) {
            fail("VideoPlayer is missing get/set Video methods");
            return;
        }
        
        Object result = invokeMethodSafely(videoPlayer, getVideoMethod);
        assertEquals("Movie", result, "VideoPlayer should return correct video name");
        
        invokeMethodSafely(videoPlayer, setVideoMethod, "New Movie");
        result = invokeMethodSafely(videoPlayer, getVideoMethod);
        assertEquals("New Movie", result, "VideoPlayer should return updated video name after setVideo");
    }

    // Tests for MusicPlayer class
    @Test
    @DisplayName("Test MusicPlayer class implements Player interface")
    void musicPlayerImplementsInterfaceTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        assertNotNull(musicPlayerClass, "MusicPlayer class should exist");
        assertTrue(implementsInterface(musicPlayerClass, "Player"), 
                "MusicPlayer should implement Player interface");
    }

    @Test
    @DisplayName("Test MusicPlayer constructor")
    void musicPlayerConstructorTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        assertNotNull(constructor, "MusicPlayer should have a constructor that accepts a String[] parameter");
    }

    @Test
    @DisplayName("Test MusicPlayer start method")
    void musicPlayerStartTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        if (constructor == null) {
            fail("MusicPlayer constructor not found");
            return;
        }
        
        Object musicPlayer = createInstanceSafely(constructor, (Object) new String[]{"First Song"});
        if (musicPlayer == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(musicPlayerClass, "start");
        Method getOnOffMethod = getMethodSafely(musicPlayerClass, "getOnOff");
        Method getCurrentSongMethod = getMethodSafely(musicPlayerClass, "getCurrentSong");
        
        if (startMethod == null || getOnOffMethod == null || getCurrentSongMethod == null) {
            fail("MusicPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(musicPlayer, startMethod);
        
        Object onOffResult = invokeMethodSafely(musicPlayer, getOnOffMethod);
        assertEquals(Boolean.TRUE, onOffResult, "MusicPlayer should be turned on after start");
        
        Object songResult = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("First Song", songResult, "MusicPlayer should play first song after start");
    }

    @Test
    @DisplayName("Test MusicPlayer stop method")
    void musicPlayerStopTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        if (constructor == null) {
            fail("MusicPlayer constructor not found");
            return;
        }
        
        Object musicPlayer = createInstanceSafely(constructor, (Object) new String[]{});
        if (musicPlayer == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method stopMethod = getMethodSafely(musicPlayerClass, "stop");
        Method getOnOffMethod = getMethodSafely(musicPlayerClass, "getOnOff");
        Method getCurrentSongMethod = getMethodSafely(musicPlayerClass, "getCurrentSong");
        
        if (stopMethod == null || getOnOffMethod == null || getCurrentSongMethod == null) {
            fail("MusicPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(musicPlayer, stopMethod);
        
        Object onOffResult = invokeMethodSafely(musicPlayer, getOnOffMethod);
        assertEquals(Boolean.FALSE, onOffResult, "MusicPlayer should be turned off after stop");
        
        Object songResult = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("", songResult, "MusicPlayer should have empty current song after stop");
    }

    @Test
    @DisplayName("Test MusicPlayer volume methods")
    void musicPlayerVolumeTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        if (constructor == null) {
            fail("MusicPlayer constructor not found");
            return;
        }
        
        Object musicPlayer = createInstanceSafely(constructor, (Object) new String[]{});
        if (musicPlayer == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method volumeUpMethod = getMethodSafely(musicPlayerClass, "volumeUp");
        Method volumeDownMethod = getMethodSafely(musicPlayerClass, "volumeDown");
        Method getVolumeMethod = getMethodSafely(musicPlayerClass, "getVolume");
        
        if (volumeUpMethod == null || volumeDownMethod == null || getVolumeMethod == null) {
            fail("MusicPlayer is missing volume methods");
            return;
        }
        
        invokeMethodSafely(musicPlayer, volumeUpMethod);
        Object result = invokeMethodSafely(musicPlayer, getVolumeMethod);
        assertEquals(1, result, "MusicPlayer volume should increase by 1 after volumeUp");
        
        invokeMethodSafely(musicPlayer, volumeUpMethod);
        result = invokeMethodSafely(musicPlayer, getVolumeMethod);
        assertEquals(2, result, "MusicPlayer volume should increase by 1 after second volumeUp");
        
        Object newMusicPlayer = createInstanceSafely(constructor, (Object) new String[]{});
        if (newMusicPlayer == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        invokeMethodSafely(newMusicPlayer, volumeDownMethod);
        result = invokeMethodSafely(newMusicPlayer, getVolumeMethod);
        assertEquals(-1, result, "MusicPlayer volume should decrease by 1 after volumeDown");
        
        invokeMethodSafely(newMusicPlayer, volumeDownMethod);
        result = invokeMethodSafely(newMusicPlayer, getVolumeMethod);
        assertEquals(-2, result, "MusicPlayer volume should decrease by 1 after second volumeDown");
    }

    @Test
    @DisplayName("Test MusicPlayer next and previous methods")
    void musicPlayerNextPreviousTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        if (constructor == null) {
            fail("MusicPlayer constructor not found");
            return;
        }
        
        Object musicPlayer = createInstanceSafely(constructor, (Object) new String[]{"First Song", "Second Song", "Third Song"});
        if (musicPlayer == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(musicPlayerClass, "start");
        Method nextMethod = getMethodSafely(musicPlayerClass, "next");
        Method previousMethod = getMethodSafely(musicPlayerClass, "previous");
        Method getCurrentSongMethod = getMethodSafely(musicPlayerClass, "getCurrentSong");
        
        if (startMethod == null || nextMethod == null || 
            previousMethod == null || getCurrentSongMethod == null) {
            fail("MusicPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(musicPlayer, startMethod);
        
        Object result = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("First Song", result, "MusicPlayer should start with first song");
        
        invokeMethodSafely(musicPlayer, nextMethod);
        result = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("Second Song", result, "MusicPlayer should play second song after next");
        
        invokeMethodSafely(musicPlayer, nextMethod);
        result = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("Third Song", result, "MusicPlayer should play third song after next");
        
        invokeMethodSafely(musicPlayer, previousMethod);
        result = invokeMethodSafely(musicPlayer, getCurrentSongMethod);
        assertEquals("Second Song", result, "MusicPlayer should play second song after previous");
    }

    // Tests for RadioPlayer class
    @Test
    @DisplayName("Test RadioPlayer class implements Player interface")
    void radioPlayerImplementsInterfaceTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        assertNotNull(radioPlayerClass, "RadioPlayer class should exist");
        assertTrue(implementsInterface(radioPlayerClass, "Player"), 
                "RadioPlayer should implement Player interface");
    }

    @Test
    @DisplayName("Test RadioPlayer constructor")
    void radioPlayerConstructorTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        assertNotNull(constructor, "RadioPlayer should have a constructor that accepts a double[] parameter");
    }

    @Test
    @DisplayName("Test RadioPlayer start method")
    void radioPlayerStartTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        if (constructor == null) {
            fail("RadioPlayer constructor not found");
            return;
        }
        
        Object radioPlayer = createInstanceSafely(constructor, (Object) new double[]{91.1});
        if (radioPlayer == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(radioPlayerClass, "start");
        Method getOnOffMethod = getMethodSafely(radioPlayerClass, "getOnOff");
        Method getStationMethod = getMethodSafely(radioPlayerClass, "getStation");
        
        if (startMethod == null || getOnOffMethod == null || getStationMethod == null) {
            fail("RadioPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(radioPlayer, startMethod);
        
        Object onOffResult = invokeMethodSafely(radioPlayer, getOnOffMethod);
        assertEquals(Boolean.TRUE, onOffResult, "RadioPlayer should be turned on after start");
        
        Object stationResult = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(91.1, ((Number)stationResult).doubleValue(), 0.1, 
                "RadioPlayer should tune to first station after start");
    }

    @Test
    @DisplayName("Test RadioPlayer stop method")
    void radioPlayerStopTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        if (constructor == null) {
            fail("RadioPlayer constructor not found");
            return;
        }
        
        Object radioPlayer = createInstanceSafely(constructor, (Object) new double[]{});
        if (radioPlayer == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method stopMethod = getMethodSafely(radioPlayerClass, "stop");
        Method getOnOffMethod = getMethodSafely(radioPlayerClass, "getOnOff");
        Method getStationMethod = getMethodSafely(radioPlayerClass, "getStation");
        
        if (stopMethod == null || getOnOffMethod == null || getStationMethod == null) {
            fail("RadioPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(radioPlayer, stopMethod);
        
        Object onOffResult = invokeMethodSafely(radioPlayer, getOnOffMethod);
        assertEquals(Boolean.FALSE, onOffResult, "RadioPlayer should be turned off after stop");
        
        Object stationResult = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(0.0, ((Number)stationResult).doubleValue(), 0.01, 
                "RadioPlayer should have 0.0 as station after stop");
    }

    @Test
    @DisplayName("Test RadioPlayer volume methods")
    void radioPlayerVolumeTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        if (constructor == null) {
            fail("RadioPlayer constructor not found");
            return;
        }
        
        Object radioPlayer = createInstanceSafely(constructor, (Object) new double[]{});
        if (radioPlayer == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method volumeUpMethod = getMethodSafely(radioPlayerClass, "volumeUp");
        Method volumeDownMethod = getMethodSafely(radioPlayerClass, "volumeDown");
        Method getVolumeMethod = getMethodSafely(radioPlayerClass, "getVolume");
        
        if (volumeUpMethod == null || volumeDownMethod == null || getVolumeMethod == null) {
            fail("RadioPlayer is missing volume methods");
            return;
        }
        
        invokeMethodSafely(radioPlayer, volumeUpMethod);
        Object result = invokeMethodSafely(radioPlayer, getVolumeMethod);
        assertEquals(2, result, "RadioPlayer volume should increase by 2 after volumeUp");
        
        invokeMethodSafely(radioPlayer, volumeUpMethod);
        result = invokeMethodSafely(radioPlayer, getVolumeMethod);
        assertEquals(4, result, "RadioPlayer volume should increase by 2 after second volumeUp");
        
        Object newRadioPlayer = createInstanceSafely(constructor, (Object) new double[]{});
        if (newRadioPlayer == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        invokeMethodSafely(newRadioPlayer, volumeDownMethod);
        result = invokeMethodSafely(newRadioPlayer, getVolumeMethod);
        assertEquals(-2, result, "RadioPlayer volume should decrease by 2 after volumeDown");
        
        invokeMethodSafely(newRadioPlayer, volumeDownMethod);
        result = invokeMethodSafely(newRadioPlayer, getVolumeMethod);
        assertEquals(-4, result, "RadioPlayer volume should decrease by 2 after second volumeDown");
    }

    @Test
    @DisplayName("Test RadioPlayer next and previous methods")
    void radioPlayerNextPreviousTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        if (constructor == null) {
            fail("RadioPlayer constructor not found");
            return;
        }
        
        Object radioPlayer = createInstanceSafely(constructor, (Object) new double[]{93.4, 102.2, 104.5});
        if (radioPlayer == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(radioPlayerClass, "start");
        Method nextMethod = getMethodSafely(radioPlayerClass, "next");
        Method previousMethod = getMethodSafely(radioPlayerClass, "previous");
        Method getStationMethod = getMethodSafely(radioPlayerClass, "getStation");
        
        if (startMethod == null || nextMethod == null || 
            previousMethod == null || getStationMethod == null) {
            fail("RadioPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(radioPlayer, startMethod);
        
        Object result = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(93.4, ((Number)result).doubleValue(), 0.1, "RadioPlayer should start with first station");
        
        invokeMethodSafely(radioPlayer, nextMethod);
        result = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(102.2, ((Number)result).doubleValue(), 0.1, "RadioPlayer should tune to second station after next");
        
        invokeMethodSafely(radioPlayer, nextMethod);
        result = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(104.5, ((Number)result).doubleValue(), 0.1, "RadioPlayer should tune to third station after next");
        
        invokeMethodSafely(radioPlayer, previousMethod);
        result = invokeMethodSafely(radioPlayer, getStationMethod);
        assertEquals(102.2, ((Number)result).doubleValue(), 0.1, "RadioPlayer should tune to second station after previous");
    }
   Create Junit test cases based on the attached that use reflect to ensure that the tests do not error out when the classes/interface, their constructors or methods, do not exist.  Instead the test should fail.
Edit

I'll create JUnit test cases that use reflection to ensure that tests fail gracefully when the expected classes, interfaces, constructors, or methods don't exist. This approach is much more robust than the current implementation which would throw exceptions rather than properly failing tests when expected components are missing.

Let me analyze the provided test file first. It appears to be testing three media player classes (MusicPlayer, RadioPlayer, VideoPlayer) that likely implement a common Player interface. The tests check various behaviors like start, stop, volume control, and media-specific operations.

Here's the implementation of the JUnit tests using reflection:

Continue
Edit

I'll continue creating the JUnit tests with reflection. Let me complete the implementation:

The JUnit tests are now complete! I've created a set of reflection-based tests that will gracefully handle cases where classes, interfaces, or methods don't exist. Here's an explanation of what these tests do:
Key Features of the Reflection-Based Tests

    Safe Class Loading: Tests first check if the required classes/interfaces exist before proceeding
        Uses getClassSafely() to attempt loading a class and returns null if not found
        All tests include explicit checks before attempting to access class members
    Safe Method and Constructor Access: Tests verify methods and constructors exist before trying to use them
        Uses getMethodSafely() and getConstructorSafely() to retrieve methods/constructors
        Returns null instead of throwing exceptions when components aren't found
    Safe Method Invocation: Tests gracefully handle method invocation failures
        Uses invokeMethodSafely() and createInstanceSafely() to handle exceptions during execution
        Each test includes appropriate null checks before proceeding
    Interface Implementation Verification: Tests properly check if classes implement the Player interface
        Uses implementsInterface() helper to check class's interface list
    Failure Messages: When a test fails due to missing components, it provides descriptive failure messages
        Each test clearly states what component was missing and why the test couldn't proceed

Structure of Tests

The test suite includes tests for:

    Player Interface: Tests for existence and required methods
    VideoPlayer Class: Tests for all required methods (start, stop, volumeUp, volumeDown, fastForward, rewind, get/setVideo)
    MusicPlayer Class: Tests for all required methods (start, stop, volumeUp, volumeDown, next, previous)
    RadioPlayer Class: Tests for all required methods (start, stop, volumeUp, volumeDown, next, previous)

Benefits Over Original Tests

The original tests had several issues that are now addressed:

    Exception Handling: Original tests would throw exceptions if classes or methods didn't exist, making debugging difficult
    Failed vs. Errored Tests: The new tests properly fail rather than error out when components are missing
    Better Diagnostics: Each test provides clear information about what's missing
    Safer Testing: The tests don't assume anything exists, improving test reliability

These reflection-based tests make it much easier to incrementally develop the media player implementation, as the tests will provide clear feedback about what's missing rather than crashing with confusing exceptions.

Would you like me to explain any specific part of the implementation in more detail?

There seems to be tests missing.  Add them.
Edit

You're right! Let me analyze what tests might be missing from the original test file and add them to our reflection-based implementation.

Upon review, I noticed we need to add more comprehensive tests for the MusicPlayer and RadioPlayer classes to match all the tests in the original file. Let me add those:

I also need to add separate tests for the VideoPlayer class's methods to fully match the original test file:

Now, let me make sure we have all the other required tests:

After careful review of the original tests, I've now added all the missing test cases. The updated reflection-based JUnit tests now include:

    Additional VideoPlayer Tests:
        Specific tests for start functionality
        Tests for stop, volumeUp, volumeDown, fastForward, rewind, and get/setVideo methods
    Additional MusicPlayer Tests:
        Tests for navigation between songs using next/previous
        Comprehensive testing of edge cases with multiple next/previous calls
    Additional RadioPlayer Tests:
        Navigation between radio stations
        Testing with different station arrays and navigation patterns
    Interface Implementation Tests:
        Specific tests to verify each player class implements the Player interface
        These use reflection to check the interfaces list of each class

These new tests use the same reflection-based approach as the earlier ones, ensuring they fail gracefully when components are missing rather than throwing exceptions. Each test includes:

    Safe class loading with proper null checks
    Safe method and constructor access
    Safe method invocation
    Descriptive failure messages

The complete test suite now covers all the test cases from the original JUnit file, but with the added benefits of:

    Better error handling
    More informative test failures
    No unexpected exceptions when classes/methods don't exist
    Clear indication of what needs to be implemented

This reflection-based approach will be much more useful for students or developers implementing these classes, as they'll get proper test failures instead of cryptic exceptions when components are missing.

Is there anything specific you'd like me to explain or any other tests you'd like me to add?


    // Additional MusicPlayer tests to match original tests
    @Test
    @DisplayName("Test MusicPlayer additional song navigation")
    void musicPlayerAdditionalSongNavigationTest() {
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        Class<?> stringArrayClass = String[].class;
        Constructor<?> constructor = getConstructorSafely(musicPlayerClass, stringArrayClass);
        if (constructor == null) {
            fail("MusicPlayer constructor not found");
            return;
        }
        
        // Test for next song navigation (like in musicPlayerNextTest2)
        Object musicPlayer1 = createInstanceSafely(constructor, (Object) new String[]{"First Song", "Second Song", "Third Song"});
        if (musicPlayer1 == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(musicPlayerClass, "start");
        Method nextMethod = getMethodSafely(musicPlayerClass, "next");
        Method getCurrentSongMethod = getMethodSafely(musicPlayerClass, "getCurrentSong");
        
        if (startMethod == null || nextMethod == null || getCurrentSongMethod == null) {
            fail("MusicPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(musicPlayer1, startMethod);
        invokeMethodSafely(musicPlayer1, nextMethod);
        invokeMethodSafely(musicPlayer1, nextMethod);
        
        Object result = invokeMethodSafely(musicPlayer1, getCurrentSongMethod);
        assertEquals("Third Song", result, "MusicPlayer should play third song after two nexts");
        
        // Test for previous song navigation (like in musicPlayerPreviousTest1)
        Object musicPlayer2 = createInstanceSafely(constructor, (Object) new String[]{"First Song", "Second Song", "Third Song"});
        if (musicPlayer2 == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        Method previousMethod = getMethodSafely(musicPlayerClass, "previous");
        if (previousMethod == null) {
            fail("MusicPlayer is missing previous method");
            return;
        }
        
        invokeMethodSafely(musicPlayer2, startMethod);
        invokeMethodSafely(musicPlayer2, nextMethod);
        invokeMethodSafely(musicPlayer2, previousMethod);
        
        result = invokeMethodSafely(musicPlayer2, getCurrentSongMethod);
        assertEquals("First Song", result, "MusicPlayer should return to first song after next then previous");
        
        // Test for previous with multiple nexts (like in musicPlayerPreviousTest2)
        Object musicPlayer3 = createInstanceSafely(constructor, (Object) new String[]{"First Song", "Second Song", "Third Song"});
        if (musicPlayer3 == null) {
            fail("Failed to create MusicPlayer instance");
            return;
        }
        
        invokeMethodSafely(musicPlayer3, startMethod);
        invokeMethodSafely(musicPlayer3, nextMethod);
        invokeMethodSafely(musicPlayer3, nextMethod);
        invokeMethodSafely(musicPlayer3, previousMethod);
        
        result = invokeMethodSafely(musicPlayer3, getCurrentSongMethod);
        assertEquals("Second Song", result, "MusicPlayer should go to second song after two nexts and one previous");
    }
    
    // Additional RadioPlayer tests to match original tests
    @Test
    @DisplayName("Test RadioPlayer additional station navigation")
    void radioPlayerAdditionalStationNavigationTest() {
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        Class<?> doubleArrayClass = double[].class;
        Constructor<?> constructor = getConstructorSafely(radioPlayerClass, doubleArrayClass);
        if (constructor == null) {
            fail("RadioPlayer constructor not found");
            return;
        }
        
        // Test for next station (like in radioPlayerNextTest1)
        Object radioPlayer1 = createInstanceSafely(constructor, (Object) new double[]{90.1, 94.3});
        if (radioPlayer1 == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method startMethod = getMethodSafely(radioPlayerClass, "start");
        Method nextMethod = getMethodSafely(radioPlayerClass, "next");
        Method getStationMethod = getMethodSafely(radioPlayerClass, "getStation");
        
        if (startMethod == null || nextMethod == null || getStationMethod == null) {
            fail("RadioPlayer is missing required methods");
            return;
        }
        
        invokeMethodSafely(radioPlayer1, startMethod);
        invokeMethodSafely(radioPlayer1, nextMethod);
        
        Object result = invokeMethodSafely(radioPlayer1, getStationMethod);
        assertEquals(94.3, ((Number)result).doubleValue(), 0.1, 
                "RadioPlayer should tune to second station after next");
                
        // Test for next station with multiple stations (like in radioPlayerNextTest2)
        Object radioPlayer2 = createInstanceSafely(constructor, (Object) new double[]{93.4, 102.2, 104.5});
        if (radioPlayer2 == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        invokeMethodSafely(radioPlayer2, startMethod);
        invokeMethodSafely(radioPlayer2, nextMethod);
        invokeMethodSafely(radioPlayer2, nextMethod);
        
        result = invokeMethodSafely(radioPlayer2, getStationMethod);
        assertEquals(104.5, ((Number)result).doubleValue(), 0.1, 
                "RadioPlayer should tune to third station after two nexts");
                
        // Test for previous station (like in radioPlayerPreviousTest1)
        Object radioPlayer3 = createInstanceSafely(constructor, (Object) new double[]{93.4, 102.2, 104.5});
        if (radioPlayer3 == null) {
            fail("Failed to create RadioPlayer instance");
            return;
        }
        
        Method previousMethod = getMethodSafely(radioPlayerClass, "previous");
        if (previousMethod == null) {
            fail("RadioPlayer is missing previous method");
            return;
        }
        
        invokeMethodSafely(radioPlayer3, startMethod);
        invokeMethodSafely(radioPlayer3, nextMethod);
        invokeMethodSafely(radioPlayer3, previousMethod);
        
        result = invokeMethodSafely(radioPlayer3, getStationMethod);
        assertEquals(93.4, ((Number)result).doubleValue(), 0.1, 
                "RadioPlayer should return to first station after next then previous");
    }

    // Additional tests for each player implementation of the Player interface
    @Test
    @DisplayName("Test each player implements Player interface")
    void allPlayersImplementInterfaceTest() {
        // Test for VideoPlayer
        String videoPlayerTestResult = "Used interface.";
        Class<?> videoPlayerClass = getClassSafely("VideoPlayer");
        Class<?> playerInterface = getClassSafely("Player");
        
        if (videoPlayerClass == null) {
            fail("VideoPlayer class not found");
            return;
        }
        
        if (playerInterface == null) {
            fail("Player interface not found");
            return;
        }
        
        if (Arrays.asList(videoPlayerClass.getInterfaces()).contains(playerInterface)) {
            videoPlayerTestResult = "Used interface.";
        } else {
            videoPlayerTestResult = "Did not use interface.";
        }
        
        assertEquals("Used interface.", videoPlayerTestResult, "VideoPlayer should implement Player interface");
        
        // Test for MusicPlayer
        String musicPlayerTestResult = "Used interface.";
        Class<?> musicPlayerClass = getClassSafely("MusicPlayer");
        
        if (musicPlayerClass == null) {
            fail("MusicPlayer class not found");
            return;
        }
        
        if (Arrays.asList(musicPlayerClass.getInterfaces()).contains(playerInterface)) {
            musicPlayerTestResult = "Used interface.";
        } else {
            musicPlayerTestResult = "Did not use interface.";
        }
        
        assertEquals("Used interface.", musicPlayerTestResult, "MusicPlayer should implement Player interface");
        
        // Test for RadioPlayer
        String radioPlayerTestResult = "Used interface.";
        Class<?> radioPlayerClass = getClassSafely("RadioPlayer");
        
        if (radioPlayerClass == null) {
            fail("RadioPlayer class not found");
            return;
        }
        
        if (Arrays.asList(radioPlayerClass.getInterfaces()).contains(playerInterface)) {
            radioPlayerTestResult = "Used interface.";
        } else {
            radioPlayerTestResult = "Did not use interface.";
        }
        
        assertEquals("Used interface.", radioPlayerTestResult, "RadioPlayer should implement Player interface");
    }
}
