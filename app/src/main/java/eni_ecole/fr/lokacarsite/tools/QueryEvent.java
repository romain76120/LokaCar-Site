package eni_ecole.fr.lokacarsite.tools;

import android.view.View;

/**
 * Created by pbontempi2017 on 28/06/2017.
 */

public class QueryEvent {
    private int idAction;
    private int idElement;
    private Object object;


    public QueryEvent(int idAction, int idElement, Object object) {
        this.idAction = idAction;
        this.idElement = idElement;
        this.object = object;
    }

    public QueryEvent(int idAction, int idElement) {
        this(idAction, idElement, null);
    }

    public QueryEvent(int idAction) {
        this(idAction, -1, null);
    }


    public int getAction() {
        return idAction;
    }

    public int getElementId() {
        return idElement;
    }

    public Object getObject() {
        return object;
    }
}
