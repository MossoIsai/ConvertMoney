# ConvertMoney
Convertidor de monedas
Cambie la blibioteca de Dagger por Koin se encontro un error: 
-Caused by: org.koin.error.NoBeanDefFoundException: No compatible definition found. Check your module definition
El cual se solucionoa agregando esta linea al factory que provee el presenter  <Contract.Presenter>

factory<Contract.Presenter> {(currencyView : Contract.View) ->
                 MainPresenter(currencyView, get())
            }
