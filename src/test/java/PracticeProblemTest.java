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
