/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import db.DatabaseBroker;

/**
 *
 * @author Ivana
 */
public abstract class GenerickaSistemskaOperacija {

    public final void izvrsi(Object objekat) throws Exception {
        DatabaseBroker broker = null;
        try {

            broker = new DatabaseBroker();
            broker.uspostaviKonekcijuPropertiesFile();

            proveriPreduslov(broker, objekat);
            izvrsi(broker, objekat);
            broker.commit();
        } catch (Exception ex) {
            if (broker != null) {
                broker.rollback();
            }
            throw ex;
        } finally {
            if (broker != null) {
                broker.raskiniKonekciju();
            }
        }

    }

    protected abstract void proveriPreduslov(DatabaseBroker broker, Object objekat) throws Exception;

    protected abstract void izvrsi(DatabaseBroker broker, Object objekat) throws Exception;

}
