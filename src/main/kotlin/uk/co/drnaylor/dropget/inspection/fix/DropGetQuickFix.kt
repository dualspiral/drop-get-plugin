package uk.co.drnaylor.dropget.inspection.fix

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiMethod
import com.intellij.refactoring.JavaRefactoringFactory

class DropGetQuickFix : LocalQuickFix {

    override fun getFamilyName(): String {
        return "Remove Prefixes"
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val element = descriptor.psiElement
        if (element is PsiMethod) {
            if (element.name.startsWith("get") && element.name.length > 3) {
                val renameString = element.name[3].toLowerCase() + element.name.drop(4)
                val rename = JavaRefactoringFactory.getInstance(project).createRename(element, renameString)
                rename.run()
            }
        }
    }
}
