package eni_ecole.fr.lokacarsite.tools;




import android.support.v4.app.Fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by pbontempi2017 on 29/06/2017.
 */

public class FragmentManager {
    private static Class fragment;

    public static Fragment getFragment() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor oCon=fragment.getConstructor();
        Fragment p =(Fragment) oCon.newInstance();
        return p;
    }

    public static void setFragment(Class fragment) {
        FragmentManager.fragment = fragment;
    }
}
