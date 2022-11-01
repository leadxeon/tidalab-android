package androidx.databinding;

import androidx.databinding.Observable;
/* loaded from: classes.dex */
public class BaseObservable implements Observable {
    public transient PropertyChangeRegistry mCallbacks;

    @Override // androidx.databinding.Observable
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new PropertyChangeRegistry();
            }
        }
        PropertyChangeRegistry propertyChangeRegistry = this.mCallbacks;
        synchronized (propertyChangeRegistry) {
            int lastIndexOf = propertyChangeRegistry.mCallbacks.lastIndexOf(onPropertyChangedCallback);
            if (lastIndexOf < 0 || propertyChangeRegistry.isRemoved(lastIndexOf)) {
                propertyChangeRegistry.mCallbacks.add(onPropertyChangedCallback);
            }
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            PropertyChangeRegistry propertyChangeRegistry = this.mCallbacks;
            if (propertyChangeRegistry != null) {
                propertyChangeRegistry.notifyCallbacks(this, i, null);
            }
        }
    }

    @Override // androidx.databinding.Observable
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback) {
        synchronized (this) {
            PropertyChangeRegistry propertyChangeRegistry = this.mCallbacks;
            if (propertyChangeRegistry != null) {
                synchronized (propertyChangeRegistry) {
                    if (propertyChangeRegistry.mNotificationLevel == 0) {
                        propertyChangeRegistry.mCallbacks.remove(onPropertyChangedCallback);
                    } else {
                        int lastIndexOf = propertyChangeRegistry.mCallbacks.lastIndexOf(onPropertyChangedCallback);
                        if (lastIndexOf >= 0) {
                            propertyChangeRegistry.setRemovalBit(lastIndexOf);
                        }
                    }
                }
            }
        }
    }
}
