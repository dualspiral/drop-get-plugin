package uk.co.drnaylor.dropget.inspection

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.JavaElementVisitor
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiMethod
import uk.co.drnaylor.dropget.inspection.fix.DropGetQuickFix

class GetInspection : com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool() {

    companion object {
        val GET_PATTERN: Regex = Regex("^get[A-Z].*")
    }

    private val fix = DropGetQuickFix()

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return ElementVisitor(holder, fix)
    }

    class ElementVisitor(private val holder: ProblemsHolder, private val fix: DropGetQuickFix) : JavaElementVisitor() {

        override fun visitMethod(method: PsiMethod?) {
            super.visitMethod(method)

            // Check for an Override annotation
            if (method != null && !method.annotations.any { it.hasQualifiedName("java.lang.Override") }) {
                if (GET_PATTERN.matches(method.name)) {
                     holder.registerProblem(method, "Needs renaming", ProblemHighlightType.WARNING, fix)
                }
            }
        }
    }
}
