package bernal.fidel.asignacion4_calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var txtResultado:TextView=findViewById(R.id.tvResultado)
    var txtEstado:TextView=findViewById(R.id.tvEstado)
    val txtEstatura:EditText=findViewById(R.id.etAltura)
    val txtPeso:EditText=findViewById(R.id.etPeso)
    val btnCalcular:Button=findViewById(R.id.btCalcular)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener {
            if (!this.txtEstatura.text.isBlank() || !this.txtPeso.text.isBlank())
            {
                //Se calcula el indice de masa corporal y se ubica un resultado
                val imcNum = this.calculaIMC(this.txtEstatura.text.toString().toDouble(),this.txtPeso.text.toString().toDouble())
                this.txtResultado.setText(imcNum.toString())
                //Estado
                val estado = this.obtenEstado(imcNum)
                this.txtEstado.setText(estado)
                //Se añade el color dependiendo del resultado
                when(estado){
                    "Bajo peso" -> this.txtEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> this.txtEstado.setBackgroundResource(R.color.ColorGreen)
                    "Sobrepeso" -> this.txtEstado.setBackgroundResource(R.color.ColorGreenish)
                    "Obesidad de grado 1" -> this.txtEstado.setBackgroundResource(R.color.ColorYellow)
                    "Obesidad de grado 2" -> this.txtEstado.setBackgroundResource(R.color.ColorOrange)
                    "Obesidad de grado 3" -> this.txtEstado.setBackgroundResource(R.color.ColorRed)
                }
            }
        }
    }
    fun calculaIMC(altura:Double, peso: Double):Double{
        val imc : Double = (peso / (Math.pow(altura, 2.0)))
        return imc

    }

    fun obtenEstado(imc:Double):String{
        when{
            imc < 18.5 -> return "Bajo peso"
            imc >= 18.5 && imc <= 24.9-> return "Saludable"
            imc >= 24.9 && imc <= 29.9-> return "Sobrepeso"
            imc >= 29.9 && imc <= 34.9-> return "Obesidad grado 1"
            imc >= 34.9 && imc <= 40 -> return "Obesidad grado 2"
            imc >= 40 -> return "Obesidad grado 3"
        }
        return "Error"
    }
}

