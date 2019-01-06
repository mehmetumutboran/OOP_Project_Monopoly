package domain.server.listeners;

public interface ReadinessChangedListener {
    void onReadinessChangedEvent(boolean readiness);
}
